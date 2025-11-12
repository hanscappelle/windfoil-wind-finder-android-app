package be.hcpl.android.speedrecords.domain.repository

import android.content.Context
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.domain.model.DEFAULT_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.DEFAULT_THRESHOLD
import be.hcpl.android.speedrecords.domain.model.DataSource
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.ModelType
import be.hcpl.android.speedrecords.domain.model.RANGE_MAX_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.RANGE_MAX_THRESHOLD
import be.hcpl.android.speedrecords.domain.model.RANGE_MIN_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.RANGE_MIN_THRESHOLD
import be.hcpl.android.speedrecords.domain.model.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ConfigRepository {

    fun retrieveIgnoredHours(): List<String>
    fun ignoreHour(time: String): Result
    fun clearIgnoredHours(): Result
    fun toggleConvertUnits(current: Boolean): Result.Settings
    fun shouldConvertUnits(): Result.Settings

    fun currentModel(type: ModelType): DataSource
    fun updateModel(model: DataSource, type: ModelType): Result
    fun toggleModel(type: ModelType): DataSource
    fun currentThreshold(): Result.Settings
    fun toggleThreshold(): Result.Settings
    fun currentForecastDays(): Result.Settings
    fun toggleForecastDays(): Result.Settings

    fun retrieveCachedWeatherData(type: ModelType): Map<LocationData, WeatherData>
    fun updateCachedWeatherData(data: Map<LocationData, WeatherData>, type: ModelType)
    fun updateLocationName(oldLocation: LocationData, newLocation: LocationData)
    fun addToCachedWeatherData(location: LocationData, weatherData: WeatherData, type: ModelType)
    fun dropFromCache(locationName: String)
    fun cachedLocations(): List<LocationData>
    fun updateCachedLocations(locations: List<LocationData>)

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
) : ConfigRepository {

    private val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    private var ignoredHours = mutableListOf<String>()

    // single source of all cached weather data in app
    private val weatherData: MutableMap<LocationData, WeatherData> = mutableMapOf()
    private val weatherDataAlt: MutableMap<LocationData, WeatherData> = mutableMapOf()

    // some initial valid data to start with for clean app plus single source of locations data
    val locationData = defaultLocations.toMutableList()

    init {
        getIgnoredHours()
    }

    private fun getIgnoredHours(): ConfigRepository.Result {
        return sharedPref.getString(PREF_KEY_IGNORED_HOURS, null)?.let { json ->
            ignoredHours = gson.fromJson(json, listOfHoursType)
            ConfigRepository.Result.Data(ignoredHours)
        } ?: ConfigRepository.Result.Failed
    }

    override fun retrieveIgnoredHours(): List<String> {
        val ignoredHours = when (val result = getIgnoredHours()) {
            is ConfigRepository.Result.Data -> result.ignoredHours
            ConfigRepository.Result.Failed,
            is ConfigRepository.Result.Settings,
            ConfigRepository.Result.Success,
                -> emptyList()
        }
        return ignoredHours
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

    override fun toggleConvertUnits(current: Boolean): ConfigRepository.Result.Settings {
        sharedPref.edit().putBoolean(PREF_KEY_CONVERT_UNITS, !current).apply()
        return ConfigRepository.Result.Settings(!current)
    }

    override fun shouldConvertUnits() = ConfigRepository.Result.Settings(sharedPref.getBoolean(PREF_KEY_CONVERT_UNITS, false))

    override fun currentModel(type: ModelType) =
        sharedPref.getString(keyForModel(type), null)?.let { json -> gson.fromJson(json, DataSource::class.java) } ?: DataSource.ECMWF

    private fun keyForModel(type: ModelType) = if (type == ModelType.MAIN) PREF_KEY_MODEL else PREF_KEY_MODEL_ALT

    override fun updateModel(model: DataSource, type: ModelType): ConfigRepository.Result {
        sharedPref.edit().putString(keyForModel(type), gson.toJson(model)).apply()
        return ConfigRepository.Result.Success
    }

    private fun keyForCachedData(type: ModelType) = if (type == ModelType.MAIN) PREF_KEY_CACHED_DATA else PREF_KEY_CACHED_DATA_ALT

    override fun retrieveCachedWeatherData(type: ModelType): Map<LocationData, WeatherData> {
        val cachedDataJson = sharedPref.getString(keyForCachedData(type), null)
        // json only supports maps with string keys so we need to enrich the data
        return if (type == ModelType.MAIN)
            updateAndReturnCachedData(weatherData, cachedDataJson)
        else
            updateAndReturnCachedData(weatherDataAlt, cachedDataJson)
    }

    private fun updateAndReturnCachedData(
        weatherData: MutableMap<LocationData, WeatherData>,
        cachedDataJson: String?,
    ): Map<LocationData, WeatherData> {
        return if (cachedDataJson != null) {
            val data: Map<String, WeatherData> = gson.fromJson(cachedDataJson, cachedDataType)
            weatherData.clear()
            weatherData.putAll(data.mapKeys { entry ->
                locationData.firstOrNull { it.name == entry.key } ?: LocationData(entry.key, 0.0, 0.0)
            })
            weatherData
        } else emptyMap()
    }

    override fun updateCachedWeatherData(data: Map<LocationData, WeatherData>, type: ModelType) {
        if (type == ModelType.MAIN) {
            weatherData.clear()
            weatherData.putAll(data)
        } else {
            weatherDataAlt.clear()
            weatherDataAlt.putAll(data)
        }
        sharedPref.edit().putString(keyForCachedData(type), gson.toJson(data.mapKeys { it.key.name }, cachedDataType)).apply()
    }

    override fun updateLocationName(
        oldLocation: LocationData,
        newLocation: LocationData,
    ) {
        // rename has to update location in both sets
        updateCachedWeatherData(weatherData.mapKeys { if (it.key.name == oldLocation.name) it.key.copy(name = newLocation.name) else it.key }
            .toMutableMap(), ModelType.MAIN)
        updateCachedWeatherData(weatherDataAlt.mapKeys { if (it.key.name == oldLocation.name) it.key.copy(name = newLocation.name) else it.key }
            .toMutableMap(), ModelType.ALT)
    }

    override fun addToCachedWeatherData(
        location: LocationData,
        weatherData: WeatherData,
        type: ModelType,
    ) {
        if (type == ModelType.MAIN) {
            this.weatherData.put(location, weatherData)
            updateCachedWeatherData(this.weatherData, type)
        } else {
            this.weatherDataAlt.put(location, weatherData)
            updateCachedWeatherData(this.weatherDataAlt, type)
        }
    }

    override fun dropFromCache(locationName: String) {
        // data drop has to be done in both sets
        updateCachedWeatherData(weatherData.filterNot { it.key.name == locationName }, ModelType.MAIN)
        updateCachedWeatherData(weatherDataAlt.filterNot { it.key.name == locationName }, ModelType.ALT)
    }

    override fun cachedLocations() = locationData

    override fun updateCachedLocations(locations: List<LocationData>) {
        locationData.clear()
        locationData.addAll(locations)
    }

    override fun toggleModel(type: ModelType): DataSource {
        // get current model
        val currentModel = currentModel(type)
        // and go to the next in line
        val nextIndex = currentModel.ordinal + 1
        val nextModel = if (nextIndex < DataSource.entries.size) DataSource.entries[nextIndex] else DataSource.entries.first()
        updateModel(nextModel, type)
        return nextModel
    }

    override fun currentThreshold() = ConfigRepository.Result.Settings(
        markWindThreshold = sharedPref.getInt(PREF_KEY_MARK_THRESHOLD, DEFAULT_THRESHOLD)
    )

    override fun toggleThreshold(): ConfigRepository.Result.Settings {
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

    override fun currentForecastDays() = ConfigRepository.Result.Settings(
        forecastDays = sharedPref.getInt(PREF_KEY_FORECAST_DAYS, DEFAULT_FORECAST_DAYS)
    )

    override fun toggleForecastDays(): ConfigRepository.Result.Settings {
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

val defaultLocations = listOf(
    LocationData(
        "Espace Fun @ Lacs De l'Eau d'Heure",
        50.1890147,
        4.3504654,
    ),
    LocationData(
        "Surfing Elephant @ De Haan",
        51.3044498,
        3.083262,
    )
)

private val listOfHoursType = object : TypeToken<List<String>>() {}.type
private val cachedDataType = object : TypeToken<Map<String, WeatherData>>() {}.type

private const val PREF_KEY_IGNORED_HOURS = "key_ignored_hours"
private const val PREF_KEY_CONVERT_UNITS = "key_convert_units"
private const val PREF_KEY_MODEL = "key_model"
private const val PREF_KEY_MODEL_ALT = "key_model_alt"
private const val PREF_KEY_FORECAST_DAYS = "key_forecast_days"
private const val PREF_KEY_MARK_THRESHOLD = "key_mark_threshold"
private const val PREF_KEY_CACHED_DATA = "key_cached_data"
private const val PREF_KEY_CACHED_DATA_ALT = "key_cached_data_alt"

