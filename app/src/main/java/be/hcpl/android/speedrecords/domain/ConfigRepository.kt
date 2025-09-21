package be.hcpl.android.speedrecords.domain

import android.content.Context
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.domain.ConfigRepository.Result.Settings
import be.hcpl.android.speedrecords.domain.model.DEFAULT_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.DEFAULT_THRESHOLD
import be.hcpl.android.speedrecords.domain.model.DataSource
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.RANGE_MAX_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.RANGE_MAX_THRESHOLD
import be.hcpl.android.speedrecords.domain.model.RANGE_MIN_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.RANGE_MIN_THRESHOLD
import be.hcpl.android.speedrecords.domain.model.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ConfigRepository {

    fun getIgnoredHours(): Result
    fun ignoreHour(time: String): Result
    fun clearIgnoredHours(): Result
    fun toggleConvertUnits(current: Boolean): Settings
    fun shouldConvertUnits(): Settings

    fun currentModel(): DataSource
    fun updateModel(model: DataSource): Result
    fun toggleModel(): DataSource
    fun currentThreshold(): Settings
    fun toggleThreshold(): Settings
    fun currentForecastDays(): Settings
    fun toggleForecastDays(): Settings

    fun retrieveCachedWeatherData(): Map<LocationData, WeatherData>
    fun updateCachedWeatherData(data: Map<LocationData, WeatherData>)
    fun updateLocationName(oldLocation: LocationData, newLocation: LocationData)
    fun addToCachedWeatherData(location: LocationData, weatherData: WeatherData)
    fun dropFromCache(locationName: String)

    sealed class Result {
        data class Data(val ignoredHours: List<String>) : Result()
        data object Success : Result()
        data object Failed : Result()
        data class Settings(
            val convertUnits: Boolean? = null,
            val markWindThreshold: Int? = null,
            val forecastDays: Int? = null,
        ) : Result()
    }
}

class ConfigRepositoryImpl(
    context: Context,
    private val gson: Gson,
    private val locationRepository: LocationRepository,
) : ConfigRepository {

    private val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    private var ignoredHours = mutableListOf<String>()

    // TODO use val here
    private var weatherData: MutableMap<LocationData, WeatherData> = mutableMapOf()

    init {
        getIgnoredHours()
    }

    override fun getIgnoredHours(): ConfigRepository.Result {
        return sharedPref.getString(PREF_KEY_IGNORED_HOURS, null)?.let { json ->
            ignoredHours = gson.fromJson(json, listOfHoursType)
            ConfigRepository.Result.Data(ignoredHours)
        } ?: ConfigRepository.Result.Failed
    }

    override fun ignoreHour(time: String): ConfigRepository.Result {
        ignoredHours.add(time.substring(11, 13))
        updateSharedPrefs()
        return ConfigRepository.Result.Success
    }

    override fun clearIgnoredHours(): ConfigRepository.Result {
        ignoredHours.clear()
        updateSharedPrefs()
        return ConfigRepository.Result.Success
    }

    private fun updateSharedPrefs(hours: List<String> = ignoredHours) {
        sharedPref.edit().putString(PREF_KEY_IGNORED_HOURS, gson.toJson(hours)).apply()
    }

    override fun toggleConvertUnits(current: Boolean): Settings {
        sharedPref.edit().putBoolean(PREF_KEY_CONVERT_UNITS, !current).apply()
        return Settings(!current)
    }

    override fun shouldConvertUnits() = Settings(sharedPref.getBoolean(PREF_KEY_CONVERT_UNITS, false))

    override fun currentModel() = sharedPref.getString(PREF_KEY_MODEL, null)?.let { json -> gson.fromJson(json, DataSource::class.java) }
        ?: DataSource.ECMWF

    override fun updateModel(model: DataSource): ConfigRepository.Result {
        sharedPref.edit().putString(PREF_KEY_MODEL, gson.toJson(model)).apply()
        return ConfigRepository.Result.Success
    }

    override fun retrieveCachedWeatherData(): Map<LocationData, WeatherData> {
        val cachedDataJson = sharedPref.getString(PREF_KEY_CACHED_DATA, null)
        // json only supports maps with string keys so we need to enrich the data
        return if (cachedDataJson != null) {
            val data: Map<String, WeatherData> = gson.fromJson(cachedDataJson, cachedDataType)
            weatherData = data.mapKeys {
                when (val result = locationRepository.locationByName(it.key)) {
                    is LocationRepository.Result.Data -> result.locations.first()
                    is LocationRepository.Result.Failed,
                    is LocationRepository.Result.Success,
                    is LocationRepository.Result.Renamed,
                        -> LocationData(it.key, 0.0, 0.0)
                }
            }.toMutableMap()
            weatherData
        } else emptyMap()
    }

    override fun updateCachedWeatherData(data: Map<LocationData, WeatherData>) {
        // TODO back to val
        weatherData = data.toMutableMap()
        sharedPref.edit().putString(PREF_KEY_CACHED_DATA, gson.toJson(data.mapKeys { it.key.name }, cachedDataType)).apply()
    }

    override fun updateLocationName(
        oldLocation: LocationData,
        newLocation: LocationData,
    ) {
        updateCachedWeatherData(weatherData.mapKeys { if (it.key.name == oldLocation.name) it.key.copy(name = newLocation.name) else it.key }.toMutableMap())
    }

    override fun addToCachedWeatherData(
        location: LocationData,
        weatherData: WeatherData,
    ) {
        this.weatherData.put(location, weatherData)
        updateCachedWeatherData(this.weatherData)
    }

    override fun dropFromCache(locationName: String) {
        updateCachedWeatherData(weatherData.filter { it.key.name == locationName })
    }

    override fun toggleModel(): DataSource {
        // get current model
        val currentModel = currentModel()
        // and go to the next in line
        val nextIndex = currentModel.ordinal + 1
        val nextModel = if (nextIndex < DataSource.entries.size) DataSource.entries[nextIndex] else DataSource.entries.first()
        updateModel(nextModel)
        return nextModel
    }

    override fun currentThreshold() = Settings(
        markWindThreshold = sharedPref.getInt(PREF_KEY_MARK_THRESHOLD, DEFAULT_THRESHOLD)
    )

    override fun toggleThreshold(): Settings {
        var current = currentThreshold().markWindThreshold ?: DEFAULT_THRESHOLD
        shrinking = when {
            current >= RANGE_MAX_THRESHOLD || shrinking && current > RANGE_MIN_THRESHOLD -> {
                current--
                true
            }

            else -> {
                current++
                false
            }
        }
        sharedPref.edit().putInt(PREF_KEY_MARK_THRESHOLD, current).apply()
        return currentThreshold()
    }

    override fun currentForecastDays() = Settings(
        forecastDays = sharedPref.getInt(PREF_KEY_FORECAST_DAYS, DEFAULT_FORECAST_DAYS)
    )

    override fun toggleForecastDays(): Settings {
        var current = currentForecastDays().forecastDays ?: DEFAULT_FORECAST_DAYS
        shrinking = when {
            current >= RANGE_MAX_FORECAST_DAYS || shrinking && current > RANGE_MIN_FORECAST_DAYS -> {
                current--
                true
            }

            else -> {
                current++
                false
            }
        }
        sharedPref.edit().putInt(PREF_KEY_FORECAST_DAYS, current).apply()
        return currentForecastDays()
    }

    var shrinking = false
}

private val listOfHoursType = object : TypeToken<List<String>>() {}.type
private val cachedDataType = object : TypeToken<Map<String, WeatherData>>() {}.type

private const val PREF_KEY_IGNORED_HOURS = "key_ignored_hours"
private const val PREF_KEY_CONVERT_UNITS = "key_convert_units"
private const val PREF_KEY_MODEL = "key_model"
private const val PREF_KEY_FORECAST_DAYS = "key_forecast_days"
private const val PREF_KEY_MARK_THRESHOLD = "key_mark_threshold"
private const val PREF_KEY_CACHED_DATA = "key_cached_data"

