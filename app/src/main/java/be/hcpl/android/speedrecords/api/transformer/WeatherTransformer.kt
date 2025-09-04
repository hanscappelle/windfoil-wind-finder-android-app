package be.hcpl.android.speedrecords.api.transformer

import be.hcpl.android.speedrecords.api.Hourly
import be.hcpl.android.speedrecords.api.WeatherResponse
import be.hcpl.android.speedrecords.domain.HourlyUnit
import be.hcpl.android.speedrecords.domain.HourlyValue
import be.hcpl.android.speedrecords.domain.WeatherData

interface WeatherTransformer {

    fun transformForecast(response: WeatherResponse?): WeatherData
}

class WeatherTransformerImpl() : WeatherTransformer {

    override fun transformForecast(response: WeatherResponse?) = WeatherData(
        latitude = response?.latitude ?: 0.0,
        longitude = response?.longitude ?: 0.0,
        timezone = response?.timezone ?: "",
        units = response?.hourly_units?.let {
            HourlyUnit(
                time = it.time,
                temperatureAt2m = it.temperature_2m,
                precipitation = it.precipitation,
                weatherCode = it.weather_code,
                cloudCover = it.cloud_cover,
                windSpeedAt10m = it.wind_speed_10m,
                windDirectionAt10m = it.wind_direction_10m,
                windGustsAt10m = it.wind_gusts_10m,
            )
        },
        hourly = transformHourlyValues(response?.hourly),
    )

    private fun transformHourlyValues(hourly: Hourly?): Map<String, HourlyValue> {
        return hourly?.time?.mapIndexedNotNull { index, value ->
            value to HourlyValue(
                time = value,
                temperatureAt2m = hourly.temperature_2m?.get(index),
                precipitation = hourly.precipitation?.get(index),
                weatherCode = hourly.weather_code?.get(index),
                cloudCover = hourly.cloud_cover?.get(index),
                windSpeedAt10m = hourly.wind_speed_10m?.get(index),
                windDirectionAt10m = hourly.wind_direction_10m?.get(index),
                windGustsAt10m = hourly.wind_gusts_10m?.get(index),
            )
        }?.toMap().orEmpty()
    }
}