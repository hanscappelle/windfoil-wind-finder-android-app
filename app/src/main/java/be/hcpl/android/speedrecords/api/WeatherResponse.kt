package be.hcpl.android.speedrecords.api

import androidx.annotation.Keep

@Keep
data class WeatherResponse(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
)