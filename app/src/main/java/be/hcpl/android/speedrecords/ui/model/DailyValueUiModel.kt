package be.hcpl.android.speedrecords.ui.model

class DailyValueUiModel(
    val time: String?,
    val displayDate: String?,
    val displayDay: String?,
    val tempMin: String,
    val tempMax: String,
    val windSpeedAt10mMin: Int?,
    val windSpeedAt10mMax: Int?,
    val windSpeedAt10mAvg: Int?,
    val weatherIcon: Int?,
    val weatherDescription: String?,
    val windDirectionAt10m: Int?,
)