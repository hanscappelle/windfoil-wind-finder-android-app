package be.hcpl.android.speedrecords.ui.transformer

import be.hcpl.android.speedrecords.domain.AssetRepository
import be.hcpl.android.speedrecords.domain.ConfigRepository
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.ui.model.DailyValueUiModel
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.model.HourlyValueUiModel
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.model.WeatherDataUiModel
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

interface WeatherDataUiModelTransformer {
    fun transformLocations(map: MutableMap<LocationData, WeatherData>): LocationUiModel
    fun transformDetail(location: LocationData, date: String, weather: WeatherData): HourlyUiModel
}

class WeatherDataUiModelTransformerImpl(
    private val configRepository: ConfigRepository,
    private val assetRepository: AssetRepository,
) : WeatherDataUiModelTransformer {

    private val dateFormatDisplay = SimpleDateFormat("EEEE", Locale.getDefault())
    private val dateFormatParse = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun transformLocations(map: MutableMap<LocationData, WeatherData>) = LocationUiModel(
        locations = map.map {
            LocationItemUiModel(
                locationName = it.key.name,
                lat = it.key.lat,
                lng = it.key.lng,
                hourlyForecast = WeatherDataUiModel(
                    latitude = it.value.latitude,
                    longitude = it.value.longitude,
                    daily = it.value.daily.mapValues {
                        DailyValueUiModel(
                            time = it.key,
                            displayDay = dateFormatParse.parse(it.key)?.let { dateFormatDisplay.format(it) } ?: "",
                            temperatureAt2mMin = it.value.temperatureAt2mMin?.toInt(),
                            temperatureAt2mMax = it.value.temperatureAt2mMax?.toInt(),
                            windSpeedAt10mMin = it.value.windSpeedAt10mMin?.toInt(),
                            windSpeedAt10mMax = it.value.windSpeedAt10mMax?.toInt(),
                            weatherIcon = assetRepository.getWeatherIcon(it.value.weatherCode),
                            weatherDescription = assetRepository.getWeatherDescription(it.value.weatherCode),
                            windDirectionAt10m = it.value.windDirectionAt10m,
                        )
                    }
                )
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
                        !ignoredHours.contains(it.key.substring(11, 13))
            }
                .mapValues {
                    HourlyValueUiModel(
                        time = it.value.time,
                        displayTime = if ((it.value.time?.length ?: 0) >= 13) it.value.time?.substring(11, 13) ?: "" else "",
                        temperatureAt2m = it.value.temperatureAt2m?.roundToInt(),
                        precipitation = it.value.precipitation,
                        cloudCover = it.value.cloudCover,
                        windSpeedAt10m = it.value.windSpeedAt10m?.roundToInt(),
                        windDirectionAt10m = it.value.windDirectionAt10m,
                        windGustsAt10m = it.value.windGustsAt10m?.roundToInt(),
                        weatherIcon = assetRepository.getWeatherIcon(it.value.weatherCode),
                        weatherDescription = assetRepository.getWeatherDescription(it.value.weatherCode),
                    )
                },
        )
    }

}