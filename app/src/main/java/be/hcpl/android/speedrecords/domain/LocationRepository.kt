package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.R
import android.content.Context
import be.hcpl.android.speedrecords.domain.LocationRepository.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface LocationRepository {

    fun getLocations(): List<LocationData>
    fun addNewLocation(string: String?): Result
    fun dropLocation(location: LocationData): Result

    sealed class Result {
        data object Success : Result()
        data object Failed : Result()
    }
}

class LocationRepositoryImpl(context: Context) : LocationRepository {

    // TODO store in shared prefs
    val sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    // TODO inject gson instead
    val gson = Gson()

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
        sharedPref.edit().putString(PREF_KEY_STORED_LOCATIONS, gson.toJson(localLocations)).apply()
        return Result.Success
    }

    override fun dropLocation(location: LocationData) = if (localLocations.remove(location)) Result.Success else Result.Failed

    override fun getLocations(): List<LocationData> {
        val storedLocations = sharedPref.getString(PREF_KEY_STORED_LOCATIONS, null)
        if (storedLocations != null) {
            localLocations = gson.fromJson<List<LocationData>>(storedLocations, listOfLocationsType).toMutableList()
        }
        // TODO return Result instead here
        return localLocations
    }
}

private val listOfLocationsType = object : TypeToken<List<LocationData>>() {}.type
private const val PREF_KEY_STORED_LOCATIONS = "key_stored_locations"

