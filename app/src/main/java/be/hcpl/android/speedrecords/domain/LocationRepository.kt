package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.R
import android.content.Context
import be.hcpl.android.speedrecords.domain.LocationRepository.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface LocationRepository {

    fun retrieveLocations(): Result
    fun addNewLocation(string: String?): Result
    fun dropLocation(location: LocationData): Result
    fun renameLocation(oldName: String, newName: String): Result

    sealed class Result {
        data class Data(val locations: List<LocationData>) : Result()
        data object Success : Result()
        data object Failed : Result()
    }
}

class LocationRepositoryImpl(context: Context) : LocationRepository {

    val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    // TODO inject gson instead
    val gson = Gson()

    // some initial valid data to start with for clean app
    var localLocations = mutableListOf(
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

    override fun addNewLocation(received: String?): Result {
        // should be received as [50.1890147, 4.3504654]
        // TODO allow adding a name to this location
        val split = received?.split(", ")
        localLocations.add(
            LocationData(
                name = received.orEmpty(),
                lat = split?.get(0)?.toDouble() ?: 0.0,
                lng = split?.get(1)?.toDouble() ?: 0.0,
            )
        )
        // and store in preferences
        persistLocations()
        return Result.Success
    }

    override fun dropLocation(location: LocationData): Result {
        return if (localLocations.remove(location)) {
            persistLocations()
            Result.Success
        } else Result.Failed
    }

    override fun retrieveLocations(): Result {
        val storedLocations = sharedPref.getString(PREF_KEY_STORED_LOCATIONS, null)
        if (storedLocations != null) {
            localLocations = gson.fromJson<List<LocationData>>(storedLocations, listOfLocationsType).toMutableList()
        }
        return Result.Data(localLocations)
    }

    private fun persistLocations() {
        sharedPref.edit().putString(PREF_KEY_STORED_LOCATIONS, gson.toJson(localLocations)).apply()
    }

    override fun renameLocation(oldName: String, newName: String): Result {
        // TODO we should really work with an ID instead of matching on name
        var matchedLocation = localLocations.find { it.name == oldName }
        return if (matchedLocation == null) {
            Result.Failed
        } else {
            var atIndex = localLocations.indexOf(matchedLocation)
            localLocations.remove(matchedLocation)
            localLocations.add(atIndex, matchedLocation.copy(name = newName))
            persistLocations()
            Result.Success
        }
    }
}

private val listOfLocationsType = object : TypeToken<List<LocationData>>() {}.type
private const val PREF_KEY_STORED_LOCATIONS = "key_stored_locations"

