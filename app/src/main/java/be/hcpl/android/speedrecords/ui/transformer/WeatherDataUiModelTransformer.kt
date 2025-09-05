package be.hcpl.android.speedrecords.ui.transformer

import be.hcpl.android.speedrecords.domain.ConfigRepository
import be.hcpl.android.speedrecords.domain.LocationData
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import be.hcpl.android.speedrecords.ui.model.LocationUiModel

interface WeatherDataUiModelTransformer {
    fun transformLocations(map: MutableMap<LocationData, WeatherData>): LocationUiModel
    fun transformDetail(location: LocationData, date: String, weather: WeatherData): HourlyUiModel
}

class WeatherDataUiModelTransformerImpl(
    private val configRepository: ConfigRepository,
) : WeatherDataUiModelTransformer {

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
    ): HourlyUiModel {
        val ignoredHours = when (val result = configRepository.getIgnoredHours()) {
            is ConfigRepository.Result.Data -> result.ignoredHours
            ConfigRepository.Result.Failed,
            ConfigRepository.Result.Success,
                -> emptyList()
        }
        return HourlyUiModel(
            locationName = location.name,
            date = date,
            hourly = weather.hourly.filter {
                // filter on selected date
                it.key.startsWith(date) &&
                // and on ignored hours
                !ignoredHours.contains(it.key.substring(11,13)) },
        )
    }
}