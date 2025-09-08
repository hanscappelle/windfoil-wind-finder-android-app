package be.hcpl.android.speedrecords.ui.model

class DailyValueUiModel(
    val time: String?,
    val displayDay: String?,
    val temperatureAt2mMin: Int?,
    val temperatureAt2mMax: Int?,
    val windSpeedAt10mMin: Int?,
    val windSpeedAt10mMax: Int?,
    val weatherIcon: Int?,
    val weatherDescription: String?,
    val windDirectionAt10m: Int?,
)