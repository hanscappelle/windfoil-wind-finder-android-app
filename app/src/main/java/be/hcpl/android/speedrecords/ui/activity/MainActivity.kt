package be.hcpl.android.speedrecords.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel
import be.hcpl.android.speedrecords.ui.screen.AppScaffold
import be.hcpl.android.speedrecords.ui.screen.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    private var recovered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        updateContent(
            locations = listOf(
                LocationUiModel(locations = emptyList()),
                LocationUiModel(locations = emptyList()),
            ),
            settings = listOf(SettingsUiModel(), SettingsUiModel()),
            refreshing = false,
        )

        recovered = savedInstanceState != null

        viewModel.state.observe(this, ::handleState)
        viewModel.events.observe(this, ::handleEvent)

        checkForReceivedLocations()
    }

    private fun checkForReceivedLocations() {
        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type != null) {
            if (TYPE_TEXT_PLAIN == type) {
                viewModel.receivedLocation(intent) // Handle text being sent
            }
        }
    }

    private fun handleEvent(event: MainViewModel.Event) {
        if (recovered) {
            // FIXME fix for now for handling events received on recovered
            recovered = false
            return
        }
        when (event) {
            is MainViewModel.Event.ShowLocationOnMap -> startActivity(Intent(Intent.ACTION_VIEW, event.uri))
            is MainViewModel.Event.OpenDetail -> startActivity(
                Intent(this, DetailActivity::class.java)
                    .putExtra(DetailActivity.Companion.KEY_SELECTED_LOCATION, event.locationName)
                    .putExtra(DetailActivity.Companion.KEY_SELECTED_DATE, event.selectedDate)
                    .putExtra(DetailActivity.Companion.KEY_SELECTED_DAY, event.selectedDay)
                    .putExtra(DetailActivity.Companion.KEY_SELECTED_MODEL, event.model.name)
            )

            is MainViewModel.Event.ShowError -> Toast.makeText(this, event.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun handleState(state: MainViewModel.State) {
        updateContent(state.locations, state.settings, state.refreshing)
    }

    private fun updateContent(
        locations: List<LocationUiModel>,
        settings: List<SettingsUiModel>,
        refreshing: Boolean,
    ) {
        setContent {
            AppScaffold(
                title = stringResource(R.string.app_name),
            ) {
                MainScreen(
                    model = locations,
                    settingsModel = settings,
                    refreshing = refreshing,
                    onRefresh = { type -> viewModel.retrieveWeatherData(type) },
                    onAddLocation = { getLocation() },
                    onUpdateLocationName = { oldName, newName -> viewModel.updateLocationName(oldName, newName) },
                    onShowLocation = { name -> viewModel.showLocation(name) },
                    onDeleteLocation = { name -> viewModel.deleteLocation(name) },
                    onOpenDetail = { name, type, date, day -> viewModel.openLocationDetail(name, type, date, day) },
                    onChangeModel = { type -> viewModel.onChangeModel(type) },
                    onChangeThreshold = { viewModel.onChangeThreshold() },
                    onChangeForecastDays = { viewModel.onChangeForecastDays() },
                    onChangeUnit = { viewModel.onChangeUnit() },
                )
            }
        }
    }

    // region location handling

    private var locationPermissionRequested = false
    private val MY_PERMISSIONS_REQUEST_LOCATION = 100

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // No explanation needed, we can request the permission.
            if (!locationPermissionRequested) {
                locationPermissionRequested = true
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            } else {
                // inform user location is needed
                Toast.makeText(MainActivity@this, R.string.err_location_no_permission, Toast.LENGTH_LONG).show()
            }
            return
        }
        registerLocationListener(LocationManager.GPS_PROVIDER)
    }

    private fun registerLocationListener(provider: String) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // remove previous listener first
        unregisterListener()
        // get current location to provide as defaults into
        // field
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        // begin by getting the last known location
        //val fetchedLocationDetails = locationManager.getLastKnownLocation(provider)
        //if (fetchedLocationDetails != null) {
        // update current location
        // TODO add location to app here
        //}
        // and start listening in order to update the location when more
        // information is retrieved
        // Register the listener with the Location Manager to receive location
        // updates
        locationManager
            .requestLocationUpdates(provider, 0, 0f, locationListener)
    }

    private fun unregisterListener() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // get current location to provide as defaults into
        // field
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        // remove previous listener first
        locationManager.removeUpdates(locationListener)
    }

    override fun onPause() {
        super.onPause()
        unregisterListener()
    }

    /**
     * listener for updating location when more data is found
     */
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            //Location(location.latitude, location.longitude)
            viewModel.receivedLocation(location)
            unregisterListener()
        }

        override fun onStatusChanged(
            provider: String, status: Int,
            extras: Bundle,
        ) {
            // nothing so far
        }

        override fun onProviderEnabled(provider: String) {
            // nothing so far
        }

        override fun onProviderDisabled(provider: String) {
            Toast.makeText(this@MainActivity, R.string.err_location_disabled, Toast.LENGTH_SHORT).show()
        }
    }

    // endregion

}

private const val TYPE_TEXT_PLAIN = "text/plain"