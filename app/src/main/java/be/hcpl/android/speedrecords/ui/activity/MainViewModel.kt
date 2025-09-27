package be.hcpl.android.speedrecords.ui.activity

import android.content.Intent
import android.location.Location
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.hcpl.android.speedrecords.domain.model.LocationData
import be.hcpl.android.speedrecords.domain.model.ModelType
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
import kotlin.math.round

private const val TEN_K = 10_000

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
    var selectedLocationName: String? = null
    var showRenameLocation = false

    init {
        doInit(ModelType.MAIN)
        doInit(ModelType.ALT)
    }

    private fun doInit(type: ModelType) {
        // initially get data from cache instead of always starting with a network call
        val data = configRepository.retrieveCachedWeatherData(type)
        if (data.isNotEmpty()) {
            refreshing = false
            refreshUi(data, type)
        } else {
            // only fetch if we have no data
            retrieveWeatherData(type)
        }
    }

    fun retrieveWeatherData(type: ModelType) {
        viewModelScope.launch {
            // show loading state
            refreshing = true
            // TODO this will remove cached data, better not to plus look for fallback
            // refreshUi(emptyMap(), type)
            // for all configured locations get update
            when (val result = retrieveForecastUseCase.invoke(type)) {
                is RetrieveForecastUseCase.Result.Failed -> handleError(result.message)
                is RetrieveForecastUseCase.Result.Success -> {
                    refreshing = false
                    refreshUi(result.data, type)
                }
            }
        }
    }

    private fun refreshUi(data: Map<LocationData, WeatherData>? = null, type: ModelType? = null) {
        val weatherData = data?.takeIf { type == ModelType.MAIN } ?: configRepository.retrieveCachedWeatherData(ModelType.MAIN)
        val weatherDataAlt = data?.takeIf { type == ModelType.ALT } ?: configRepository.retrieveCachedWeatherData(ModelType.ALT)
        state.postValue(
            State(
                refreshing = refreshing,
                locations = listOf(
                    uiModelTransformer.transformLocations(weatherData),//.copy(isRefreshing = refreshing),
                    uiModelTransformer.transformLocations(weatherDataAlt),//.copy(isRefreshing = refreshing),
                ),
                settings = uiModelTransformer.transformSettings(),
                showRenameDialog = showRenameLocation,
                locationName = selectedLocationName ?: "New Location Name",
            )
        )
    }

    fun receivedLocation(intent: Intent) {
        var sharedText: String? = intent.getStringExtra(Intent.EXTRA_TEXT)
        when (val result = createLocationUseCase(sharedText)) {
            is CreateLocationUseCase.Result.Failed -> handleError(result.message)
            CreateLocationUseCase.Result.Success -> {
                // adding new locations requires data refresh
                retrieveWeatherData(ModelType.MAIN)
                retrieveWeatherData(ModelType.ALT)
            }
        }
    }

    fun receivedLocation(location: Location) {
        val uniqueLocationName = "New Location ${round(location.latitude * TEN_K) / TEN_K};${round(location.longitude * TEN_K) / TEN_K}"
        val locationText = "${location.latitude}, ${location.longitude}, $uniqueLocationName"
        when (val result = createLocationUseCase(locationText)) {
            is CreateLocationUseCase.Result.Failed -> handleError(result.message)
            CreateLocationUseCase.Result.Success -> {
                // adding new locations requires data refresh on all data
                retrieveWeatherData(ModelType.MAIN)
                retrieveWeatherData(ModelType.ALT)
                // and allow user to enter a new name for this new location also
                onShowRenameLocation(uniqueLocationName)
            }
        }
    }

    fun updateLocationName(oldName: String, newName: String) {
        showRenameLocation = false
        refreshUi()
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
            DeleteLocationUseCase.Result.Success -> refreshUi()
            is DeleteLocationUseCase.Result.Failed -> handleError(result.message)
        }
    }

    fun openLocationDetail(name: String, type: ModelType, date: String, day: String) {
        events.postValue(OpenDetail(name, type, date, day))
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

    fun onChangeModel(type: ModelType) {
        configRepository.toggleModel(type)
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

    fun onShowRenameLocation(locationName: String) {
        showRenameLocation = true
        selectedLocationName = locationName
        refreshUi()
    }

    data class State(
        val locations: List<LocationUiModel> = listOf(
            LocationUiModel(
                locations = emptyList(), //isRefreshing = true
            ),
            LocationUiModel(
                locations = emptyList(), //isRefreshing = true
            )
        ),
        val settings: List<SettingsUiModel> = listOf(SettingsUiModel(), SettingsUiModel()),
        val refreshing: Boolean = false,
        val showRenameDialog: Boolean = false,
        val locationName: String = "New Location Name",
    )

    sealed class Event {
        data class ShowLocationOnMap(val uri: Uri) : Event()
        data class OpenDetail(
            val locationName: String,
            val model: ModelType,
            val selectedDate: String,
            val selectedDay: String,
        ) : Event()

        data class ShowError(val message: String) : Event()
    }
}