package be.hcpl.android.speedrecords

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.screen.DetailScreen
import be.hcpl.android.speedrecords.ui.screen.MainScreen
import be.hcpl.android.speedrecords.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : ComponentActivity() {

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // get selected location from intent
        val location = intent.getStringExtra(KEY_SELECTED_LOCATION)
        val date = intent.getStringExtra(KEY_SELECTED_DATE)
        viewModel.updateLocation(location, date)

        viewModel.state.observe(this, ::handleState)
    }

    private fun handleState(state: DetailViewModel.State) {
        updateContent(state.model)
    }

    // TODO fix back handling from toolbar

    @OptIn(ExperimentalMaterial3Api::class)
    private fun updateContent(hourlyUiModel: HourlyUiModel) {
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text("Navigation example")
                            },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                        )
                    },
                ) { innerPadding ->
                    DetailScreen(
                        modifier = Modifier.Companion.padding(innerPadding),
                        model = hourlyUiModel,
                    )
                }
            }
        }
    }

    companion object {
        const val KEY_SELECTED_DATE = "key_selected_date"
        const val KEY_SELECTED_LOCATION = "key_selected_location"
    }

}