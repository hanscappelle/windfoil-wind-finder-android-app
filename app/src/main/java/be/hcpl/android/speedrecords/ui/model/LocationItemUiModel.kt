package be.hcpl.android.speedrecords.ui.model

data class LocationItemUiModel(
    val locationName: String,
    val lat: Double,
    val lng: Double,
    val hourlyForecast: WeatherDataUiModel?,
)