package be.hcpl.android.speedrecords.domain.usecase

import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.domain.repository.ConfigRepository
import be.hcpl.android.speedrecords.domain.repository.LocationRepository
import be.hcpl.android.speedrecords.domain.repository.WeatherRepository
import kotlin.collections.forEach

class RetrieveForecastUseCase(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val configRepository: ConfigRepository,
) {

    suspend fun invoke(): Result {
        val data = locationRepository.retrieveLocations()
        return when (data) {
            is LocationRepository.Result.Data -> handleReceivedLocations(data.locations)
            is LocationRepository.Result.Failed,
            is LocationRepository.Result.Success,
            is LocationRepository.Result.Renamed,
                -> handleError()

        }
    }

    private fun handleError(message: String? = null) = Result.Failed(message)

    private suspend fun handleReceivedLocations(locations: List<LocationData>) : Result{
        val weatherData = mutableMapOf<LocationData, WeatherData>()
        locations.forEach { location ->
            // get forecast weather data
            val result = weatherRepository.forecast(location)
            when (result) {
                is WeatherRepository.Result.Success -> {
                    weatherData.put(location, result.data)
                }

                is WeatherRepository.Result.Failed -> handleError(result.reason)
            }
        }
        configRepository.updateCachedWeatherData(weatherData)
        return Result.Success(weatherData)
    }

    sealed class Result {
        data class Success(val data: Map<LocationData, WeatherData>) : Result()
        data class Failed(val message: String? = null) : Result()
    }
}