package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.R

interface AssetRepository {
    fun getWeatherDescription(code: Int?): String?
    fun getWeatherIcon(code: Int?): Int?
}

class AssetRepositoryImpl() : AssetRepository {

    override fun getWeatherDescription(code: Int?) = when (code) {
        0 -> "Sunny"
        1 -> "Mainly Sunny"
        2 -> "Partly Cloudy"
        3 -> "Cloudy"
        45 -> "Foggy"
        48 -> "Rime Fog"
        51 -> "Light Drizzle"
        53 -> "Drizzle"
        55 -> "Heavy Drizzle"
        56 -> "Light Freezing Drizzle"
        57 -> "Freezing Drizzle"
        61 -> "Light Rain"
        63 -> "Rain"
        65 -> "Heavy Rain"
        66 -> "Light Freezing Rain"
        67 -> "Freezing Rain"
        71 -> "Light Snow"
        73 -> "Snow"
        75 -> "Heavy Snow"
        77 -> "Snow Grains"
        80 -> "Light Showers"
        81 -> "Showers"
        82 -> "Heavy Showers"
        85 -> "Light Snow Showers"
        86 -> "Snow Showers"
        95 -> "Thunderstorm"
        96 -> "Light Thunderstorms With Hail"
        99 -> "Thunderstorm With Hail"
        else -> "Unknown"

    }

    override fun getWeatherIcon(code: Int?) = when (code ?: 0) {
        in 0..1 -> R.drawable.wmo_01d
        2 -> R.drawable.wmo_02d
        3 -> R.drawable.wmo_03d
        in 45..48 -> R.drawable.wmo_50d
        in 51..57 -> R.drawable.wmo_09d
        in 61..67 -> R.drawable.wmo_10d
        in 71..77 -> R.drawable.wmo_13d
        in 80..82 -> R.drawable.wmo_09d
        in 85..86 -> R.drawable.wmo_13d
        in 95..99 -> R.drawable.wmo_11d
        else -> R.drawable.wmo_01d // TODO have some backup for unknown weather codes
    }

}