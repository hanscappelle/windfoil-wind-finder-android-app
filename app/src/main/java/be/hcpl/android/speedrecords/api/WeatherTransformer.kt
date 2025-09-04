package be.hcpl.android.speedrecords.api

import be.hcpl.android.speedrecords.domain.WeatherData

interface WeatherTransformer {

    fun transformForecast(response: WeatherResponse?): WeatherData
}

class WeatherTransformerImpl() : WeatherTransformer {

    override fun transformForecast(response: WeatherResponse?): WeatherData {
        return WeatherData(
            latitude = response?.latitude ?: 0.0,
            longitude = response?.longitude ?: 0.0,
            timezone = response?.timezone ?: ""
        )
    }
}