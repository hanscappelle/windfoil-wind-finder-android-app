package be.hcpl.android.speedrecords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.activity.compose.setContent
import be.hcpl.android.speedrecords.ui.screen.LocationUiModel
import be.hcpl.android.speedrecords.ui.screen.MainScreen
import be.hcpl.android.speedrecords.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {

    // add viewModel to get all weather data from API
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        updateContent(LocationUiModel(locations = emptyList()))
        viewModel.state.observeForever(::handleState)
        viewModel.events.observeForever(::handleEvent)
    }

    private fun handleEvent(event: MainViewModel.Event) {
        when(event){
            MainViewModel.Event.AddNewLocationInfo -> showLocationInfoPopUp()
        }
    }

    private fun showLocationInfoPopUp() {

    }

    private fun updateContent(locations: LocationUiModel) {
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        model = locations,
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateAllData()
    }

    private fun handleState(state: MainViewModel.State) {
        updateContent(state.locations)
    }
}