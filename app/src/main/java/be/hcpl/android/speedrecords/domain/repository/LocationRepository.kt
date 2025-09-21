package be.hcpl.android.speedrecords.domain.repository

import android.content.Context
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.repository.LocationRepository.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface LocationRepository {

    fun retrieveLocations(): Result
    fun locationByName(name: String): Result
    fun addNewLocation(string: String?): Result
    fun dropLocation(location: LocationData): Result
    fun renameLocation(oldName: String, newName: String): Result

    sealed class Result {
        data class Data(val locations: List<LocationData>) : Result()
        data class Success(val location: LocationData) : Result()
        data class Renamed(val oldLocation: LocationData, val newLocation: LocationData) : Result()
        data class Failed(val message: String? = null) : Result()
    }
}

class LocationRepositoryImpl(
    context: Context,
    private val gson: Gson,
    private val configRepository: ConfigRepository,
) : LocationRepository {

    private val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    init {
        retrieveLocations()
    }

    override fun locationByName(name: String) =
        configRepository.cachedLocations().find { it.name == name }?.let { Result.Success(it) } ?: Result.Failed()

    override fun addNewLocation(received: String?): Result {
        // should be received as [50.1890147, 4.3504654] or [50.1890147, 4.3504654, location name]
        // TODO allow adding a name to this location (also in UI)
        return try {
            val changed = configRepository.cachedLocations().toMutableList()
            val split = received?.split(", ")
            val newLocation = extractLocationInfo(split, received)
            changed.add(newLocation)
            // and store in preferences
            persistLocations(changed)
            Result.Success(newLocation)
        } catch (_: Exception) {
            Result.Failed("wrong location format")
        }
    }

    // 89.98089, 12.90909, Name
    private fun extractLocationInfo(split: List<String>?, received: String?) =
        LocationData(
            name = if ((split?.size ?: 0) > 2) split?.get(2).orEmpty() else received.orEmpty(),
            lat = split?.get(0)?.replace(",", ".")?.toDouble() ?: 0.0,
            lng = split?.get(1)?.replace(",", ".")?.toDouble() ?: 0.0,
        )

    override fun dropLocation(location: LocationData): Result {
        val changed = configRepository.cachedLocations().toMutableList()
        return if (changed.remove(location)) {
            persistLocations(changed)
            Result.Success(location)
        } else Result.Failed()
    }

    override fun retrieveLocations(): Result {
        val storedLocations = sharedPref.getString(PREF_KEY_STORED_LOCATIONS, null)
        if (storedLocations != null) {
            val parsedLocations = gson.fromJson<List<LocationData>>(storedLocations, listOfLocationsType).toMutableList()
            if (parsedLocations.isNotEmpty()) {
                configRepository.updateCachedLocations(parsedLocations)
            }
        }
        return Result.Data(configRepository.cachedLocations())
    }

    private fun persistLocations(locations: List<LocationData> = configRepository.cachedLocations()) {
        configRepository.updateCachedLocations(locations)
        sharedPref.edit().putString(PREF_KEY_STORED_LOCATIONS, gson.toJson(locations)).apply()
    }

    override fun renameLocation(oldName: String, newName: String): Result {
        // TODO we should really work with an ID instead of matching on name
        var matchedLocation = configRepository.cachedLocations().find { it.name == oldName }
        return if (matchedLocation == null) {
            Result.Failed()
        } else {
            var atIndex = configRepository.cachedLocations().indexOf(matchedLocation)
            val changed = configRepository.cachedLocations().toMutableList()
            changed.remove(matchedLocation)
            val newLocation = matchedLocation.copy(name = newName)
            changed.add(atIndex, newLocation)
            persistLocations(changed)
            Result.Renamed(matchedLocation, newLocation)
        }
    }
}

private val listOfLocationsType = object : TypeToken<List<LocationData>>() {}.type
private const val PREF_KEY_STORED_LOCATIONS = "key_stored_locations"

