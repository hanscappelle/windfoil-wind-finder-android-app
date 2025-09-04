package be.hcpl.android.speedrecords

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.domain.WeatherRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    val state = MutableLiveData<State>()

    init {
        doInit()
    }

    // TODO pass location and date range

    private fun doInit() {
        viewModelScope.launch {
            val result = weatherRepository.forecast()
            Log.d("TAG", "fetched result is $result")
            when (result) {
                is WeatherRepository.Result.Success -> state.postValue(State(result.data))
                is WeatherRepository.Result.Failed -> Log.d("TAG", "failed to get data with error ${result.reason}")
            }
        }
    }

    fun update() {
        doInit()
    }

    data class State(
        val weatherData: WeatherData? = null,
    )
}