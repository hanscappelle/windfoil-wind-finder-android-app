package be.hcpl.android.speedrecords.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.hcpl.android.speedrecords.domain.ConfigRepository
import be.hcpl.android.speedrecords.domain.LocationRepository
import be.hcpl.android.speedrecords.domain.WeatherRepository
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel
import be.hcpl.android.speedrecords.ui.transformer.WeatherDataUiModelTransformer
import kotlinx.coroutines.launch

class MainViewModel(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
    private val configRepository: ConfigRepository,
    private val uiModelTransformer: WeatherDataUiModelTransformer,
) : ViewModel() {

    val state = MutableLiveData<State>()
    val events = MutableLiveData<Event>()

    val weatherData: MutableMap<LocationData, WeatherData> = mutableMapOf()
    var refreshing = true
    var convertToFahrenheit = configRepository.shouldConvertUnits().convertUnits

    init {
        doInit()
    }

    private fun doInit() {
        viewModelScope.launch {
            // show loading state
            refreshing = true
            weatherData.clear()
            refreshUi()
            // for all configured locations get update
            val data = locationRepository.retrieveLocations()
            when (data) {
                is LocationRepository.Result.Data -> handleReceivedLocations(data.locations)
                is LocationRepository.Result.Failed,
                LocationRepository.Result.Success,
                    -> handleError()
            }
        }
    }

    suspend fun handleReceivedLocations(locations: List<LocationData>) {
        locations.forEach { location ->
            // get forecast weather data
            val result = weatherRepository.forecast(location)
            when (result) {
                is WeatherRepository.Result.Success -> {
                    weatherData.put(location, result.data)
                    refreshing = false
                    refreshUi()
                }

                is WeatherRepository.Result.Failed -> handleError(result.reason)
            }
        }
    }

    fun updateAllData() {
        doInit()
    }

    private fun refreshUi() {
        state.postValue(
            State(
                locations = uiModelTransformer.transformLocations(weatherData).copy(isRefreshing = refreshing),
                settings = uiModelTransformer.transformSettings(configRepository.shouldConvertUnits()),
            )
        )
    }

    fun receivedLocation(intent: Intent) {
        var sharedText: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        val result = locationRepository.addNewLocation(sharedText)
        when (result) {
            LocationRepository.Result.Success -> updateAllData()
            is LocationRepository.Result.Failed -> handleError(result.message)
            is LocationRepository.Result.Data -> handleError()
        }
    }

    fun updateLocationName(oldName: String, newName: String) {
        val result = locationRepository.renameLocation(oldName, newName)
        when (result) {
            LocationRepository.Result.Success -> updateAllData()
            is LocationRepository.Result.Failed -> handleError(result.message)
            is LocationRepository.Result.Data -> handleError()
        }
    }

    fun showLocation(name: String) {
        val matchedLocation = weatherData.keys.find { it.name == name }
        matchedLocation?.let {
            events.postValue(Event.ShowLocationOnMap(uri = Uri.parse("https://maps.google.com/maps?q=loc:" + matchedLocation.lat + "," + matchedLocation.lng + " (" + matchedLocation.name + ")")))
        }
    }

    fun deleteLocation(name: String) {
        val matchedLocation = weatherData.keys.find { it.name == name }
        matchedLocation?.let {
            when (locationRepository.dropLocation(matchedLocation)) {
                LocationRepository.Result.Success -> updateAllData()
                is LocationRepository.Result.Data,
                is LocationRepository.Result.Failed,
                    -> handleError()
            }
        }
    }

    fun openLocationDetail(name: String, date: String, day: String) {
        events.postValue(Event.OpenDetail(name, date, day))
    }

    fun handleError(message: String? = null) {
        refreshing = false
        refreshUi()
        events.postValue(Event.ShowError(uiModelTransformer.transformError(message)))
    }

    fun changeUnit() {
        // TODO implement full settings screen with more options
        convertToFahrenheit = configRepository.toggleConvertUnits(convertToFahrenheit).convertUnits
        refreshUi()
    }

    fun changeModel(){
        configRepository.toggleModel()
        refreshUi()
    }

    data class State(
        val locations: LocationUiModel = LocationUiModel(
            locations = emptyList(), isRefreshing = true
        ),
        val settings: SettingsUiModel = SettingsUiModel(),
    )

    sealed class Event {
        data class ShowLocationOnMap(val uri: Uri) : Event()
        data class OpenDetail(
            val locationName: String,
            val selectedDate: String,
            val selectedDay: String,
        ) : Event()

        data class ShowError(val message: String) : Event()
    }
}