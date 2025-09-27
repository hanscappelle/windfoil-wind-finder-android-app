package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.model.DataSource

data class HourlyUiModel(
    val locationName: String,
    val weatherModel: DataSource,
    val date: String,
    val day: String,
    val hourly: Map<String, HourlyValueUiModel>,
)