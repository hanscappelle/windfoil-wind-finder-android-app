package be.hcpl.android.speedrecords.domain

import android.content.Context
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.domain.ConfigRepository.Result.Settings
import be.hcpl.android.speedrecords.domain.model.DataSource
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

    sealed class Result {
        data class Data(val ignoredHours: List<String>) : Result()
        data object Success : Result()
        data object Failed : Result()
        data class Settings(val convertUnits: Boolean) : Result()
    }
}

class ConfigRepositoryImpl(
    context: Context,
    private val gson: Gson,
) : ConfigRepository {

    private val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    private var ignoredHours = mutableListOf<String>()

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

    override fun toggleModel(): DataSource {
        // get current model
        val currentModel = currentModel()
        // and go to the next in line
        val nextIndex = currentModel.ordinal + 1
        val nextModel = if (nextIndex < DataSource.entries.size) DataSource.entries[nextIndex] else DataSource.entries.first()
        updateModel(nextModel)
        return nextModel
    }
}

private val listOfHoursType = object : TypeToken<List<String>>() {}.type
private const val PREF_KEY_IGNORED_HOURS = "key_ignored_hours"
private const val PREF_KEY_CONVERT_UNITS = "key_convert_units"
private const val PREF_KEY_MODEL = "key_model"

