package be.hcpl.android.speedrecords.domain

import be.hcpl.android.speedrecords.api.OpenWeatherService
import be.hcpl.android.speedrecords.api.WeatherResponse
import be.hcpl.android.speedrecords.api.WeatherTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface WeatherRepository {

    suspend fun forecast(): Result

    sealed class Result {
        data class Success(val data: WeatherData) : Result()
        data class Failed(val reason: String) : Result()
    }
}

class WeatherRepositoryImpl(
    private val weatherService: OpenWeatherService,
    private val transformer: WeatherTransformer,
) : WeatherRepository {

    // injected instead
    //val apiInterface = RetrofitInstance.getInstance().create(ApiInterface::class.java)

    override suspend fun forecast(): WeatherRepository.Result {
        // using coroutines
        val response: Response<WeatherResponse> = weatherService.forecast()
        return if (response.isSuccessful && response.body() != null) {
            WeatherRepository.Result.Success(transformer.transformForecast(response.body()))
        } else {
            WeatherRepository.Result.Failed(response.message())
        }
    }
    // using callbacks
    //val call = weatherService.forecastWithCallback() : Call<WeatherResponse>
    //call.enqueue(object : Callback<WeatherResponse> {
    //    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
    //        if (response.isSuccessful && response.body()!=null){
    //            WeatherRepository.Result.Success(transformer.transformForecast(response.body()))
    //        }
    //    }
    //
    //    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
    //        WeatherRepository.Result.Failed(t.message.toString())
    //    }
    //})
}