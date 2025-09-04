package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.WeatherResponse
import be.hcpl.android.speedrecords.api.transformer.WeatherTransformer
import be.hcpl.android.speedrecords.domain.WeatherRepository.Result
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar

interface WeatherRepository {

    suspend fun forecast(locationData: LocationData): Result

    sealed class Result {
        data class Success(val data: WeatherData) : Result()
        data class Failed(val reason: String) : Result()
    }
}

class WeatherRepositoryImpl(
    private val weatherService: OpenWeatherService,
    private val transformer: WeatherTransformer,
) : WeatherRepository {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

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
            Result.Success(transformer.transformForecast(response.body()))
        } else {
            Result.Failed(response.message())
        }
    }

}