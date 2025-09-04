package be.hcpl.android.speedrecords.ui.transformer

import be.hcpl.android.speedrecords.domain.LocationData
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import be.hcpl.android.speedrecords.ui.model.LocationUiModel

interface WeatherDataUiModelTransformer {
    fun transform(map: MutableMap<LocationData, WeatherData>): LocationUiModel
}

class WeatherDataUiModelTransformerImpl() : WeatherDataUiModelTransformer {
    override fun transform(map: MutableMap<LocationData, WeatherData>) = LocationUiModel(
        locations = map.map {
            LocationItemUiModel(
                locationName = it.key.name,
                lat = it.key.lat,
                lng = it.key.lng,
                hourlyForecast = it.value,
            )

        }
    )
}