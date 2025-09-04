package be.hcpl.android.speedrecords.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.domain.WeatherData

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: LocationUiModel,
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
        modifier = modifier
            .padding(8.dp)
    ) {
        // TODO allow for managing favorite locations
        // TODO translations needed here
        Text(
            text = "Your Favorite locations",
            fontSize = 24.sp,
        )

        LocationOverview(model)
    }

    // TODO on detail view of a location show all values per hour
}

// TODO allow configuration of threshold for warnings, colors, and what timeslots to include

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