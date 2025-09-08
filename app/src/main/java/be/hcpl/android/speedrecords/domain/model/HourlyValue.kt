package be.hcpl.android.speedrecords.domain.model

data class HourlyValue(
    val time: String?,
    val temperatureAt2m: Double?,
    val precipitation: Double?,
    val weatherCode: Int?,
    val cloudCover: Int?,
    val windSpeedAt10m: Double?,
    val windDirectionAt10m: Int?,
    val windGustsAt10m: Double?,
)