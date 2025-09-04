package be.hcpl.android.speedrecords.ui.screen

import android.graphics.Paint
import be.hcpl.android.speedrecords.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: LocationUiModel,
) {

    // TODO extract dialog
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = { openDialog.value = false }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(8.dp),
                    modifier = Modifier
                    .padding(32.dp)

                ) {
                    Text(
                        text = stringResource(R.string.info_add_location),
                        fontSize = 20.sp,
                        //textAlign = Alignment.CenterHorizontally,
                    )
                    Text(
                        text = stringResource(R.string.info_add_location_extra),
                        fontSize = 20.sp,
                        //textAlign = Alignment.CenterHorizontally,
                    )
                    Button(
                        onClick = { openDialog.value = false },
                    ) {
                        Text(text = stringResource(R.string.ok))
                    }
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
        modifier = modifier
            .padding(8.dp)
    ) {

        LocationsOverviewHeader({ openDialog.value = true })

        LocationOverview(model)
    }

    // TODO on detail view of a location show all values per hour
}

@Composable
fun LocationsOverviewHeader(onAddNewLocation: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
    ) {

        Text(
            text = stringResource(id = R.string.title_favourite_locations),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )

        // TODO allow for managing favorite locations
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_add),
            contentDescription = stringResource(id = R.string.a11y_add_location),
            modifier = Modifier.clickable(onClick = onAddNewLocation)
        )
    }
}

// TODO allow configuration of threshold for warnings, colors, and what timeslots to include in detail

@Composable
fun LocationOverview(model: LocationUiModel) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
    ) {
        model.locations.forEach { location ->
            item {
                Text(
                    text = location.locationName,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
                // TODO allow deletion of location here
                // TODO add link to show location on map here
                //String uri = "geo:" + latitude + ","
                //+longitude + "?q=" + latitude
                //+ "," + longitude;
                //startActivity(new Intent(android.content.Intent.ACTION_VIEW,
                //    Uri.parse(uri)));
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