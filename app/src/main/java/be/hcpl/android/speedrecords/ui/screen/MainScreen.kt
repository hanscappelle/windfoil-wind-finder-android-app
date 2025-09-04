package be.hcpl.android.speedrecords.ui.screen

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        // TODO allow for picking favorite locations
        // TODO translations needed here
        Text("Your Favorite locations")

        LocationOverview(model)
    }

    // TODO on detail view of a location show all values per hour
}
// TODO on overview show per location and show min/max values for that day

// TODO allow configuration of threshold for warnings, colors, and more

@Composable
fun LocationOverview(model: LocationUiModel) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
    ) {
        model.locations.forEach { location ->
            item {
                LocationItem(location)
            }
        }
    }
}

@Composable
fun LocationItem(model: LocationItemUiModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
    ) {
        
        Text(model.locationName, modifier = Modifier.height(48.dp))
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