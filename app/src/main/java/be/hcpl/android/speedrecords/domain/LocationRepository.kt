package be.hcpl.android.speedrecords.domain

interface LocationRepository {

    fun getLocations(): List<LocationData>
}

class LocationRepositoryImpl : LocationRepository {

    override fun getLocations(): List<LocationData> {
        // TODO get actual data from settings
        return listOf(
            LocationData(
                "Brussels",
                50.8371,
                4.3676,
            ),
            LocationData(
                "Lacs De l'Eau d'Heure",
                50.1890147,
                4.3504654,
            ),
            LocationData(
                "Surfing Elephant De Haan",
                51.3044498,
                3.083262,
            )

        )
    }
}

