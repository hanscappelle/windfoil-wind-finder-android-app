package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.model.DataSource
import be.hcpl.android.speedrecords.domain.model.UnitType

data class SettingsUiModel(
    val unit: UnitType = UnitType.Celsius,
    val source: DataSource = DataSource.ECMWF,
)
