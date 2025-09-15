package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.model.DEFAULT_THRESHOLD

data class LocationItemUiModel(
    val locationName: String,
    val lat: Double,
    val lng: Double,
    val hourlyForecast: WeatherDataUiModel?,
    val windThreshold: Int = DEFAULT_THRESHOLD,
)