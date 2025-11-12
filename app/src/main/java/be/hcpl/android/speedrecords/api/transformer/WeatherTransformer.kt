package be.hcpl.android.speedrecords.api.transformer

import be.hcpl.android.speedrecords.api.contract.ErrorResponse
import be.hcpl.android.speedrecords.api.contract.Hourly
import be.hcpl.android.speedrecords.api.contract.WeatherResponse
import be.hcpl.android.speedrecords.domain.model.DailyValue
import be.hcpl.android.speedrecords.domain.model.HourlyUnit
import be.hcpl.android.speedrecords.domain.model.HourlyValue
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.domain.repository.ConfigRepository
import com.google.gson.Gson
import okhttp3.ResponseBody
import kotlin.text.substring

interface WeatherTransformer {

    fun transformForecast(response: WeatherResponse?): WeatherData

    fun transformForecast(response: ResponseBody?): ErrorResponse?
}

class WeatherTransformerImpl(
    private val gson: Gson,
    private val configRepository: ConfigRepository,
) : WeatherTransformer {

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

    override fun transformForecast(response: ResponseBody?): ErrorResponse? {
        return response?.string()?.let {
            gson.fromJson<ErrorResponse>(it, ErrorResponse::class.java)
        }
    }

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

    private fun calculateDailyValues(hourly: Map<String, HourlyValue>): Map<String, DailyValue> {
        // only take not ignored hours into account for avg/min/max
        val ignoredHours = configRepository.retrieveIgnoredHours()
        return hourly.values
            .filter {
                // Ensure time is not null at this point (time is used as key)
                it.time != null
                        // and also filter out ignored hours
                        && !ignoredHours.contains(it.time.substring(11, 13))
            }
            .groupBy {
                // group received values by date (first 10 chars)
                it.time!!.substring(0, 10) // Extract date part "yyyy-MM-dd"
            }
            .mapValues { entry ->
                // entry.key is the date string "yyyy-MM-dd"
                // entry.value is List<HourlyValue> for that date
                //val sumForDate = entry.value.sumOf { it.someIntValue!! } // Values are already filtered for non-null
                // when data is missing key is still provided but values are null
                DailyValue(
                    time = entry.key,
                    temperatureAt2mMin = entry.value.minOf { it.temperatureAt2m ?: 0.0 },
                    temperatureAt2mMax = entry.value.maxOf { it.temperatureAt2m ?: 0.0 },
                    windSpeedAt10mMin = entry.value.minOf { it.windSpeedAt10m ?: 0.0 },
                    windSpeedAt10mMax = entry.value.maxOf { it.windSpeedAt10m ?: 0.0 },
                    windSpeedAt10mAvg = entry.value.map { it.windSpeedAt10m ?: 0.0 }.average(),
                    windGustsAt10mMax = entry.value.maxOf { it.windGustsAt10m ?: 0.0 },
                    // use weather code that is most common of that day (first)
                    weatherCode = resolveMostCommonValueFor(entry.value.map { it.weatherCode }),
                    windDirectionAt10m = resolveMostCommonValueFor(entry.value.map { it.windDirectionAt10m }),
                )
            }
    }

    private fun resolveMostCommonValueFor(values: List<Int?>): Int? {
        val frequencies = values.groupingBy { it }.eachCount()
        val maxFrequency = frequencies.values.maxOrNull() ?: 0
        return frequencies.filter { it.value == maxFrequency }.keys.firstOrNull()
    }
}