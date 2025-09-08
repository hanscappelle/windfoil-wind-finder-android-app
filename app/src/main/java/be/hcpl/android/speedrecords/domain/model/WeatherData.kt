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

data class HourlyValue(
    val time: String?,
    val displayTime: String?,
    val temperatureAt2m: Double?,
    val precipitation: Double?,
    val weatherCode: Int?,
    val cloudCover: Int?,
    val windSpeedAt10m: Double?,
    val windDirectionAt10m: Int?,
    val windGustsAt10m: Double?,
)

/**
 * min and max values stored per day & calculated by transformer
 */
data class DailyValue(
    val time: String?,
    val displayDay: String?,
    val temperatureAt2mMin: Double?,
    val temperatureAt2mMax: Double?,
    val windSpeedAt10mMin: Double?,
    val windSpeedAt10mMax: Double?,
    val windGustsAt10mMax: Double?,
)