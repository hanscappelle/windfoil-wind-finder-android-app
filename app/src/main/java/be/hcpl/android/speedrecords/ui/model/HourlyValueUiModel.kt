package be.hcpl.android.speedrecords.ui.model

import be.hcpl.android.speedrecords.domain.model.DEFAULT_THRESHOLD

data class HourlyValueUiModel(
    val time: String?,
    val displayTime: String,
    val temperature: String,
    val precipitation: Double?,
    val cloudCover: Int?,
    val windSpeedAt10m: Int?,
    val windDirectionAt10m: Int?,
    val windGustsAt10m: Int?,
    val weatherIcon: Int?,
    val weatherDescription: String?,
    val windThreshold: Int = DEFAULT_THRESHOLD,
)
