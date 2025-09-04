package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.ui.screen.LocationUiModel

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