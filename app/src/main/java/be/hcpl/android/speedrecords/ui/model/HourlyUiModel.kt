package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.HourlyValue

data class HourlyUiModel(
    val locationName: String,
    val date: String,
    val hourly: Map<String, HourlyValue>,
)