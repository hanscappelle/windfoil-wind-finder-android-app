package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.WeatherData

data class LocationItemUiModel(
    val locationName: String,
    val lat: Double,
    val lng: Double,
    val hourlyForecast: WeatherData?, // TODO limit to what is really needed here
)