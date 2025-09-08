package be.hcpl.android.speedrecords.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.hcpl.android.speedrecords.domain.ConfigRepository
import be.hcpl.android.speedrecords.domain.LocationRepository
import be.hcpl.android.speedrecords.domain.WeatherRepository
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.transformer.WeatherDataUiModelTransformer

class DetailViewModel(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val configRepository: ConfigRepository,
    private val transformer: WeatherDataUiModelTransformer,
) : ViewModel() {

    val state = MutableLiveData<State>()

    private var selectedDate: String? = null
    private var locationData: LocationData? = null
    private var weatherData: WeatherData? = null

    fun updateLocation(locationName: String?, date: String?) {
        date?.let { selectedDate = it }
        locationName?.let { name ->
            when (val result = locationRepository.locationByName(name)) {
                is LocationRepository.Result.Data -> updateLocation(result)
                is LocationRepository.Result.Failed,
                LocationRepository.Result.Success,
                    -> Unit
            }
        }

    }

    private fun updateLocation(result: LocationRepository.Result.Data) {
        result.locations.firstOrNull()?.let {
            locationData = it
            when (val result = weatherRepository.cachedForecastFor(it)) {
                is WeatherRepository.Result.Failed -> Unit
                is WeatherRepository.Result.Success -> {
                    weatherData = result.data
                    refreshUi()
                }
            }
        }
    }

    private fun refreshUi() {
        if (listOf(locationData, selectedDate, weatherData).none { it == null }) {
            state.postValue(
                State(
                    model = transformer.transformDetail(
                        location = locationData!!,
                        date = selectedDate!!,
                        weather = weatherData!!
                    )
                )
            )
        }
    }

    fun clearIgnoredHours() {
        when (configRepository.clearIgnoredHours()) {
            is ConfigRepository.Result.Data,
            is ConfigRepository.Result.Settings,
            ConfigRepository.Result.Failed,
                -> Unit

            ConfigRepository.Result.Success -> refreshUi()
        }

    }

    fun onIgnoreHour(time: String) {
        when (configRepository.ignoreHour(time)) {
            is ConfigRepository.Result.Data,
            is ConfigRepository.Result.Settings,
            ConfigRepository.Result.Failed,
                -> Unit

            ConfigRepository.Result.Success -> refreshUi()
        }
    }

    data class State(
        val model: HourlyUiModel,
    )
}