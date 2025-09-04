package be.hcpl.android.speedrecords

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.hcpl.android.speedrecords.domain.LocationData
import be.hcpl.android.speedrecords.domain.LocationRepository
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.domain.WeatherRepository
import be.hcpl.android.speedrecords.ui.screen.LocationUiModel
import be.hcpl.android.speedrecords.ui.transformer.WeatherDataUiModelTransformer
import kotlinx.coroutines.launch

class MainViewModel(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val uiModelTransformer: WeatherDataUiModelTransformer,
) : ViewModel() {

    val state = MutableLiveData<State>()
    val events = MutableLiveData<Event>()

    val weatherData: MutableMap<LocationData, WeatherData> = mutableMapOf()

    init {
        doInit()
    }

    private fun doInit() {
        viewModelScope.launch {
            // for all configured locations
            val locations = locationRepository.getLocations()
            locations.forEach { location ->
                // get forecast weather data
                val result = weatherRepository.forecast(location)
                Log.d("TAG", "fetched result is $result")
                when (result) {
                    is WeatherRepository.Result.Success -> {
                        weatherData.put(location, result.data)
                        refreshUi()
                    }
                    is WeatherRepository.Result.Failed -> Log.d("TAG", "failed to get data with error ${result.reason}")
                }
            }
        }
    }

    fun updateAllData() {
       doInit()
    }

    private fun refreshUi(){
        state.postValue(State(locations = uiModelTransformer.transform(weatherData)))
    }

    fun receivedLocation(intent: Intent) {
        var sharedText: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        Log.d("TEST", "received $sharedText")
        val result = locationRepository.addNewLocation(sharedText)
        when(result) {
            LocationRepository.Result.Success -> {
                updateAllData()
            }
        }
    }

    data class State(
        val locations: LocationUiModel = LocationUiModel(emptyList())
    )

    sealed class Event{
        data object AddNewLocationInfo : Event()
    }
}