package be.hcpl.android.speedrecords.domain.model

data class WeatherData(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val units: HourlyUnit?,
    val hourly: Map<String, HourlyValue>,
    val daily: Map<String, DailyValue>,
)

data class HourlyUnit(
    val time: String?,
    val temperatureAt2m: String?,
    val precipitation: String?,
    val weatherCode: String?,
    val cloudCover: String?,
    val windSpeedAt10m: String?,
    val windDirectionAt10m: String?,
    val windGustsAt10m: String?,
)