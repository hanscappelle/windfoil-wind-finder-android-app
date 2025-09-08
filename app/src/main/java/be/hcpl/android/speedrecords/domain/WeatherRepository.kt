package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.contract.WeatherResponse
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformer
import be.hcpl.android.speedrecords.domain.WeatherRepository.Result
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

interface WeatherRepository {

    suspend fun forecast(locationData: LocationData): Result

    fun cachedForecastFor(locationData: LocationData): Result

    sealed class Result {
        data class Success(val data: WeatherData) : Result()
        data class Failed(val reason: String) : Result()
    }
}

class WeatherRepositoryImpl(
    private val weatherService: OpenWeatherService,
    private val transformer: WeatherTransformer,
) : WeatherRepository {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private val cachedData: MutableMap<LocationData, WeatherData> = mutableMapOf()

    override suspend fun forecast(locationData: LocationData): Result {
        // using coroutines, always give the results for today + 10 days
        val today = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.DAY_OF_YEAR, 10)
        val response: Response<WeatherResponse> = weatherService.forecast(
            latitude = locationData.lat,
            longitude = locationData.lng,
            startDate = dateFormat.format(today.time),
            endDate = dateFormat.format(endDate.time),
        )
        return if (response.isSuccessful && response.body() != null) {
            val weatherData = transformer.transformForecast(response.body())
            cachedData.put(locationData, weatherData)
            Result.Success(weatherData)
        } else {
            Result.Failed(response.message())
        }
    }

    override fun cachedForecastFor(locationData: LocationData) =
        cachedData.get(locationData)?.let { Result.Success(it) } ?: Result.Failed("no cached data")
}