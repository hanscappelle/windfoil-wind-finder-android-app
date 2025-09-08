package be.hcpl.android.speedrecords.ui.model

data class HourlyUiModel(
    val locationName: String,
    val date: String,
    val day: String,
    val hourly: Map<String, HourlyValueUiModel>,
)