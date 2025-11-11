package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import be.hcpl.android.speedrecords.ui.model.LocationUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationOverview(
    model: LocationUiModel,
    modifier: Modifier = Modifier,
    onRenameLocation: (String) -> Unit,
    onShowLocation: (String) -> Unit,
    onDeleteLocation: (String) -> Unit,
    onOpenDetail: (String, String, String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier,
    ) {
        model.locations.forEach { location ->
            LocationHeader(
                location = location,
                onRenameLocation = { onRenameLocation(location.locationName) },
                onShowLocation = { onShowLocation(location.locationName) },
                onDeleteLocation = { onDeleteLocation(location.locationName) },
            )
            LocationItem(
                location,
                onOpenDetail = onOpenDetail,
            )
        }
    }
}