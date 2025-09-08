package be.hcpl.android.speedrecords.api.transformer

import be.hcpl.android.speedrecords.api.contract.Hourly
import be.hcpl.android.speedrecords.api.contract.WeatherResponse
import be.hcpl.android.speedrecords.domain.model.DailyValue
import be.hcpl.android.speedrecords.domain.model.HourlyUnit
import be.hcpl.android.speedrecords.domain.model.HourlyValue
import be.hcpl.android.speedrecords.domain.model.WeatherData
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.text.substring

interface WeatherTransformer {

    fun transformForecast(response: WeatherResponse?): WeatherData
}

class WeatherTransformerImpl() : WeatherTransformer {

    private val dateFormatDisplay = SimpleDateFormat("EEEE", Locale.getDefault())
    private val dateFormatParse = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun transformForecast(response: WeatherResponse?): WeatherData {
        val hourly = transformHourlyValues(response?.hourly)
        return WeatherData(
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
            hourly = hourly,
            daily = calculateDailyValues(hourly),
        )
    }

    private fun transformHourlyValues(hourly: Hourly?): Map<String, HourlyValue> {
        return hourly?.time?.mapIndexedNotNull { index, value ->
            value to HourlyValue(
                time = value,
                displayTime = if (value.length >= 13) value.substring(11, 13) else value,
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

    private fun calculateDailyValues(hourly: Map<String, HourlyValue>): Map<String, DailyValue> {
        return hourly.values
            .filter { it.time != null } // Ensure time and value are not null
            .groupBy {
                it.time!!.substring(0, 10) // Extract date part "yyyy-MM-dd"
            }
            .mapValues { entry ->
                // entry.key is the date string "yyyy-MM-dd"
                // entry.value is List<HourlyValue> for that date
                //val sumForDate = entry.value.sumOf { it.someIntValue!! } // Values are already filtered for non-null
                DailyValue(
                    time = entry.key,
                    displayDay = dateFormatParse.parse(entry.key)?.let { dateFormatDisplay.format(it) } ?: "",
                    temperatureAt2mMin = entry.value.minOf { it.temperatureAt2m!! },
                    temperatureAt2mMax = entry.value.maxOf { it.temperatureAt2m!! },
                    windSpeedAt10mMin = entry.value.minOf { it.windSpeedAt10m!! },
                    windSpeedAt10mMax = entry.value.maxOf { it.windSpeedAt10m!! },
                    windGustsAt10mMax = entry.value.maxOf { it.windGustsAt10m!! },
                )
            }
    }
}