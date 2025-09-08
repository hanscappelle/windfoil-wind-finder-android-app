package be.hcpl.android.speedrecords.ui.model

data class HourlyUiModel(
    val locationName: String,
    val date: String,
    val hourly: Map<String, HourlyValueUiModel>,
)