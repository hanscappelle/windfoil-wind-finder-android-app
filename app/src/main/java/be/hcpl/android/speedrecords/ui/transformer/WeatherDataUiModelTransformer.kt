package be.hcpl.android.speedrecords.ui.transformer

import be.hcpl.android.speedrecords.domain.LocationData
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import be.hcpl.android.speedrecords.ui.model.LocationUiModel

interface WeatherDataUiModelTransformer {
    fun transformLocations(map: MutableMap<LocationData, WeatherData>): LocationUiModel
    fun transformDetail(location: LocationData, date: String, weather: WeatherData): HourlyUiModel
}

class WeatherDataUiModelTransformerImpl() : WeatherDataUiModelTransformer {

    override fun transformLocations(map: MutableMap<LocationData, WeatherData>) = LocationUiModel(
        locations = map.map {
            LocationItemUiModel(
                locationName = it.key.name,
                lat = it.key.lat,
                lng = it.key.lng,
                hourlyForecast = it.value,
            )

        }
    )

    override fun transformDetail(
        location: LocationData,
        date: String,
        weather: WeatherData,
    ) = HourlyUiModel(
        locationName = location.name,
        date = date,
        hourly = weather.hourly,

        )
}