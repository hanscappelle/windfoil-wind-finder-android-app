package be.hcpl.android.speedrecords.domain.repository

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.contract.WeatherResponse
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformer
import be.hcpl.android.speedrecords.domain.model.DEFAULT_FORECAST_DAYS
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.domain.repository.WeatherRepository.Result
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

interface WeatherRepository {

    suspend fun forecast(locationData: LocationData): Result
    fun cachedForecastFor(locationData: LocationData): Result

    sealed class Result {
        data class Success(val data: WeatherData) : Result()
        data class Failed(val message: String) : Result()
    }
}

class WeatherRepositoryImpl(
    private val weatherService: OpenWeatherService,
    private val transformer: WeatherTransformer,
    private val configRepository: ConfigRepository,
) : WeatherRepository {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override suspend fun forecast(locationData: LocationData): Result {
        // using coroutines, always give the results for today + 10 days
        val today = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        val forecastDays = configRepository.currentForecastDays().forecastDays ?: DEFAULT_FORECAST_DAYS
        endDate.add(Calendar.DAY_OF_YEAR, forecastDays)
        val response: Response<WeatherResponse> = weatherService.forecast(
            latitude = locationData.lat,
            longitude = locationData.lng,
            startDate = dateFormat.format(today.time),
            endDate = dateFormat.format(endDate.time),
            model = configRepository.currentModel().type,
        )
        return if (response.isSuccessful && response.body() != null) {
            val weatherData = transformer.transformForecast(response.body())
            configRepository.addToCachedWeatherData(locationData, weatherData)
            Result.Success(weatherData)
        } else {
            Result.Failed(response.message())
        }
    }

    override fun cachedForecastFor(locationData: LocationData) =
        configRepository.retrieveCachedWeatherData()[locationData]?.let { Result.Success(it) } ?: Result.Failed("no cached data")

}