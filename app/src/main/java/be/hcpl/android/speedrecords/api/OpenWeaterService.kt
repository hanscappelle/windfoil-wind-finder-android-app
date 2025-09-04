package be.hcpl.android.speedrecords.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface OpenWeatherService {

    // TODO add params (location & time)
    @GET("v1/forecast?latitude=50.85045&longitude=4.34878&hourly=temperature_2m,precipitation,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m,wind_gusts_10m&models=ecmwf_ifs025&wind_speed_unit=kn&start_date=2025-08-27&end_date=2025-09-10")
    suspend fun forecast(): Response<WeatherResponse>

    //fun forecastWithCallback(): Call<WeatherResponse>
}