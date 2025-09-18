package be.hcpl.android.speedrecords.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.res.stringResource
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
        updateContent(LocationUiModel(locations = emptyList()), settings = SettingsUiModel())

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
        if( recovered ) {
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
            )

            is MainViewModel.Event.ShowError -> Toast.makeText(this, event.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun handleState(state: MainViewModel.State) {
        updateContent(state.locations, state.settings)
    }

    private fun updateContent(
        locations: LocationUiModel,
        settings: SettingsUiModel,
        ) {
        setContent {
            //AppTheme {
            //    Scaffold(
            //        modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppScaffold(
                title = stringResource(R.string.app_name),
            ) {
                    MainScreen(
                        //modifier = Modifier.padding(innerPadding),
                        model = locations,
                        settingsModel = settings,
                        onRefresh = { viewModel.updateAllData() },
                        onUpdateLocationName = { oldName, newName -> viewModel.updateLocationName(oldName, newName) },
                        onShowLocation = { name -> viewModel.showLocation(name) },
                        onDeleteLocation = { name -> viewModel.deleteLocation(name) },
                        onOpenDetail = {name, date, day -> viewModel.openLocationDetail(name, date, day)},
                        onChangeModel = { viewModel.onChangeModel() },
                        onChangeThreshold = { viewModel.onChangeThreshold() },
                        onChangeForecastDays = { viewModel.onChangeForecastDays() },
                        onChangeUnit = { viewModel.onChangeUnit() },
                    )
                //}
            }
        }
    }

}

private const val TYPE_TEXT_PLAIN = "text/plain"