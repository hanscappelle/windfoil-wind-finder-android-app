package be.hcpl.android.speedrecords.ui.screen

import be.hcpl.android.speedrecords.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.ui.view.NameLocationDialog
import be.hcpl.android.speedrecords.ui.view.InfoDialog
import be.hcpl.android.speedrecords.ui.view.LocationHeader
import be.hcpl.android.speedrecords.ui.view.LocationsOverviewHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: LocationUiModel,
    onRefresh: () -> Unit = {},
) {
    // some dialogs
    val openInfoDialog = remember { mutableStateOf(false) }
    InfoDialog(openInfoDialog)

    val addLocationDialog = remember { mutableStateOf(false) }
    NameLocationDialog(addLocationDialog)

    // content
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
        modifier = modifier.padding(8.dp),
    ) {

        LocationsOverviewHeader(
            onAddNewLocation = { openInfoDialog.value = true },
            onRefresh = onRefresh,
        )

        LocationOverview(
            model = model,
            onRenameLocation = { addLocationDialog.value = true }
        )
    }

    // TODO on detail view of a location show all values per hour
}

// TODO allow configuration of threshold for warnings, colors, and what timeslots to include in detail

@Composable
fun LocationOverview(
    model: LocationUiModel,
    onRenameLocation: (String) -> Unit = {},
) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
    ) {
        model.locations.forEach { location ->
            item {
                LocationHeader(
                    location = location,
                    onRenameLocation = { onRenameLocation(location.locationName) },
                )
            }
            item {
                LocationItem(location)
            }
        }
    }
}

@Composable
fun LocationItem(model: LocationItemUiModel) {
    model.hourlyForecast?.daily?.forEach {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(8.dp),
            modifier = Modifier
                .padding(4.dp)
                .border(BorderStroke(width = 1.dp, Color.LightGray)),
        ) {
            // TODO include day of week
            Text(
                text = "${it.value.time}",
                modifier = Modifier.weight(1f),
            )
            // display min and max wind speeds
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "min ${it.value.windSpeedAt10mMin} knots")
                Text(text = "max ${it.value.windSpeedAt10mMax} knots")
            }
            // display min and max temperatures
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "min ${it.value.temperatureAt2mMin} °C")
                Text(text = "max ${it.value.temperatureAt2mMax} °C")
            }
        }
    }
}

data class LocationUiModel(
    val locations: List<LocationItemUiModel>,
)

data class LocationItemUiModel(
    val locationName: String,
    val lat: Double,
    val lng: Double,
    val hourlyForecast: WeatherData?,
)