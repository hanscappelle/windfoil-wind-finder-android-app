package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.domain.LocationRepository.Result

interface LocationRepository {

    fun getLocations(): List<LocationData>
    fun addNewLocation(string: String?): Result
    fun dropLocation(location: LocationData): Result

    sealed class Result {
        data object Success : Result()
        data object Failed : Result()
    }
}

class LocationRepositoryImpl : LocationRepository {

    // TODO store in shared prefs
    //val sharedPref = activity?.getSharedPreferences(
    //    getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    val localLocations = mutableListOf(
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
        return Result.Success
    }

    override fun dropLocation(location: LocationData) = if (localLocations.remove(location)) Result.Success else Result.Failed

    override fun getLocations(): List<LocationData> {
        // TODO get actual data from settings instead and let user manage these
        return localLocations.toList()
    }
}

