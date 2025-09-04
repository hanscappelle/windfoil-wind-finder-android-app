package be.hcpl.android.speedrecords

import android.R
import android.R.attr.action
import android.R.attr.type
import android.content.Intent
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

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        updateContent(LocationUiModel(locations = emptyList()))
        viewModel.state.observeForever(::handleState)
        viewModel.events.observeForever(::handleEvent)

        checkForReceivedLocations()
    }

    private fun checkForReceivedLocations() {
        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type != null) {
            if ("text/plain" == type) {
                viewModel.receivedLocation(intent) // Handle text being sent
            }
        }
    }

    private fun handleEvent(event: MainViewModel.Event) {
        when(event){
            MainViewModel.Event.AddNewLocationInfo -> Unit
        }
    }

    private fun handleState(state: MainViewModel.State) {
        updateContent(state.locations)
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
        //viewModel.updateAllData() // TODO refresh needed here?
    }

}