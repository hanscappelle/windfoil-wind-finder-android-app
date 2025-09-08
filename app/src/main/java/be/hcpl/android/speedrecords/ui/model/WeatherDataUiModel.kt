package be.hcpl.android.speedrecords.ui.model

class WeatherDataUiModel(
    val latitude: Double,
    val longitude: Double,
    val daily: Map<String, DailyValueUiModel>,
)