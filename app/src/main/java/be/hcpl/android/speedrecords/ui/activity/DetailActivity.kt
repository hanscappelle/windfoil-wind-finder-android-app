package be.hcpl.android.speedrecords.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.screen.AppScaffold
import be.hcpl.android.speedrecords.ui.screen.DetailScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : ComponentActivity() {

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // add back navigation the old way
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // get selected location from intent
        val location = intent.getStringExtra(KEY_SELECTED_LOCATION)
        val date = intent.getStringExtra(KEY_SELECTED_DATE)
        val day = intent.getStringExtra(KEY_SELECTED_DAY)
        viewModel.updateLocation(location, date, day)

        viewModel.state.observe(this, ::handleState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            //onBackPressedDispatcher.onBackPressed()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleState(state: DetailViewModel.State) {
        updateContent(state.model)
    }

    private fun updateContent(model: HourlyUiModel) {
        setContent {
            AppScaffold(
                title = "${model.date} - ${model.day}",
                onBack = { finish() },
            ) {
                    DetailScreen(
                        model = model,
                        onRestoreAllHours = { viewModel.clearIgnoredHours() },
                        onIgnoreHour = { time -> viewModel.onIgnoreHour(time) },
                    )
                }
        }
    }

    companion object {
        const val KEY_SELECTED_DATE = "key_selected_date"
        const val KEY_SELECTED_DAY = "key_selected_day"
        const val KEY_SELECTED_LOCATION = "key_selected_location"
    }

}