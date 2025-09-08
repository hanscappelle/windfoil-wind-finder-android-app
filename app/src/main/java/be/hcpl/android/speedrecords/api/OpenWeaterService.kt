package be.hcpl.android.speedrecords.api

import be.hcpl.android.speedrecords.api.contract.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    // TODO also allow for unit configuration in the future here

    @GET("v1/forecast?hourly=temperature_2m,precipitation,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m,wind_gusts_10m&models=ecmwf_ifs025&wind_speed_unit=kn")
    suspend fun forecast(
        @Query("latitude") latitude: Double = 50.85045,
        @Query("longitude") longitude: Double = 4.34878,
        @Query("start_date") startDate: String = "2025-09-01",
        @Query("end_date") endDate: String = "2025-09-11",
    ): Response<WeatherResponse>

}