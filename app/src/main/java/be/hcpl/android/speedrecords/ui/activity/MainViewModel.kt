package be.hcpl.android.speedrecords.ui.activity

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.WeatherData
import be.hcpl.android.speedrecords.domain.repository.ConfigRepository
import be.hcpl.android.speedrecords.domain.usecase.CreateLocationUseCase
import be.hcpl.android.speedrecords.domain.usecase.DeleteLocationUseCase
import be.hcpl.android.speedrecords.domain.usecase.RenameLocationUseCase
import be.hcpl.android.speedrecords.domain.usecase.RetrieveForecastUseCase
import be.hcpl.android.speedrecords.domain.usecase.ShowLocationUseCase
import be.hcpl.android.speedrecords.ui.activity.MainViewModel.Event.*
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel
import be.hcpl.android.speedrecords.ui.transformer.WeatherDataUiModelTransformer
import kotlinx.coroutines.launch

class MainViewModel(
    private val configRepository: ConfigRepository,
    private val uiModelTransformer: WeatherDataUiModelTransformer,
    private val renameLocationUseCase: RenameLocationUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val retrieveForecastUseCase: RetrieveForecastUseCase,
    private val createLocationUseCase: CreateLocationUseCase,
    private val showLocationUseCase: ShowLocationUseCase,
) : ViewModel() {

    val state = MutableLiveData<State>()
    val events = MutableLiveData<Event>()

    var refreshing = true
    var convertToFahrenheit = configRepository.shouldConvertUnits().convertUnits

    init {
        doInit()
    }

    private fun doInit() {
        // initially get data from cache instead of always starting with a network call
        val data = configRepository.retrieveCachedWeatherData()
        if (data.isNotEmpty()) {
            refreshing = false
            refreshUi(data)
        } else {
            // only fetch if we have no data
            retrieveWeatherData()
        }
    }

    fun retrieveWeatherData() {
        viewModelScope.launch {
            // show loading state
            refreshing = true
            refreshUi(emptyMap())
            // for all configured locations get update
            when (val result = retrieveForecastUseCase.invoke()) {
                is RetrieveForecastUseCase.Result.Failed -> handleError(result.message)
                is RetrieveForecastUseCase.Result.Success -> {
                    refreshing = false
                    refreshUi(result.data)
                }
            }
        }
    }

    private fun refreshUi(data: Map<LocationData, WeatherData>? = null) {
        val weatherData = data ?: configRepository.retrieveCachedWeatherData()
        state.postValue(
            State(
                locations = uiModelTransformer.transformLocations(weatherData).copy(isRefreshing = refreshing),
                settings = uiModelTransformer.transformSettings(),
            )
        )
    }

    fun receivedLocation(intent: Intent) {
        var sharedText: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        when (val result = createLocationUseCase(sharedText)) {
            is CreateLocationUseCase.Result.Failed -> handleError(result.message)
            CreateLocationUseCase.Result.Success -> retrieveWeatherData() // adding new locations requires data refresh
        }
    }

    fun updateLocationName(oldName: String, newName: String) {
        when (val result = renameLocationUseCase(oldName, newName)) {
            is RenameLocationUseCase.Result.Success -> refreshUi()
            is RenameLocationUseCase.Result.Failed -> handleError(result.message)
        }
    }

    fun showLocation(name: String) {
        when (val result = showLocationUseCase(name)) {
            is ShowLocationUseCase.Result.Success -> events.postValue(ShowLocationOnMap(uri = result.uri))
            is ShowLocationUseCase.Result.Failed -> handleError(result.message)
        }
    }

    fun deleteLocation(name: String) {
        when (val result = deleteLocationUseCase(name)) {
            DeleteLocationUseCase.Result.Success -> retrieveWeatherData()
            is DeleteLocationUseCase.Result.Failed -> handleError(result.message)
        }
    }

    fun openLocationDetail(name: String, date: String, day: String) {
        events.postValue(OpenDetail(name, date, day))
    }

    fun handleError(message: String? = null) {
        refreshing = false
        refreshUi()
        events.postValue(ShowError(uiModelTransformer.transformError(message)))
    }

    fun onChangeUnit() {
        convertToFahrenheit = configRepository.toggleConvertUnits(convertToFahrenheit == true).convertUnits
        refreshUi()
    }

    fun onChangeModel() {
        configRepository.toggleModel()
        refreshUi()
    }

    fun onChangeThreshold() {
        configRepository.toggleThreshold()
        refreshUi()
    }

    fun onChangeForecastDays() {
        configRepository.toggleForecastDays()
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