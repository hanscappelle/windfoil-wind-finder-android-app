package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.model.DataSource
import be.hcpl.android.speedrecords.domain.model.UnitType

data class SettingsUiModel(
    val source: DataSource = DataSource.ECMWF,
    val days: Int = 10,
    val threshold: Int = 10,
    val unit: UnitType = UnitType.Celsius,
)
