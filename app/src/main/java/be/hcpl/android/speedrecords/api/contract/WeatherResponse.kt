package be.hcpl.android.speedrecords.api.contract

import androidx.annotation.Keep

@Keep
data class WeatherResponse(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val elevation: Double?,
    val hourly_units: HourlyUnit?,
    val hourly: Hourly?,
)

@Keep
data class HourlyUnit(
    val time: String?,
    val temperature_2m: String?,
    val precipitation: String?,
    val weather_code: String?,
    val cloud_cover: String?,
    val wind_speed_10m: String?,
    val wind_direction_10m: String?,
    val wind_gusts_10m: String?,
)

@Keep
data class Hourly(
    val time: List<String>?,
    val temperature_2m: List<Double>?,
    val precipitation: List<Double>?,
    val weather_code: List<Int>?,
    val cloud_cover: List<Int>?,
    val wind_speed_10m: List<Double>?,
    val wind_direction_10m: List<Int>?,
    val wind_gusts_10m: List<Double>?,

)