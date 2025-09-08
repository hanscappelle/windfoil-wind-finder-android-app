package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.ui.model.LocationUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationOverview(
    model: LocationUiModel,
    onRefresh: () -> Unit,
    onRenameLocation: (String) -> Unit = {},
    onShowLocation: (String) -> Unit = {},
    onDeleteLocation: (String) -> Unit = {},
    onOpenDetail: (String, String) -> Unit = { _, _ -> },
) {
    PullToRefreshBox(
        isRefreshing = model.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxWidth(),
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
                        onShowLocation = { onShowLocation(location.locationName) },
                        onDeleteLocation = { onDeleteLocation(location.locationName) },
                    )
                }
                item {
                    LocationItem(
                        location,
                        onOpenDetail = onOpenDetail,
                    )
                }
            }
        }
    }
}