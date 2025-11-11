package be.hcpl.android.speedrecords.domain.model

/**
 * min and max values stored per day & calculated by transformer
 */
data class DailyValue(
    val time: String?,
    val temperatureAt2mMin: Double?,
    val temperatureAt2mMax: Double?,
    val windSpeedAt10mMin: Double?,
    val windSpeedAt10mMax: Double?,
    val windSpeedAt10mAvg: Double?,
    val windGustsAt10mMax: Double?,
    val weatherCode: Int?,
    val windDirectionAt10m: Int?,
)