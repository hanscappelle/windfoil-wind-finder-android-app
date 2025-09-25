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
    //onRefresh: () -> Unit,
    onRenameLocation: (String) -> Unit,
    onShowLocation: (String) -> Unit,
    onDeleteLocation: (String) -> Unit,
    onOpenDetail: (String, String, String) -> Unit,
) {
    //PullToRefreshBox(
    //    isRefreshing = model.isRefreshing,
    //    onRefresh = onRefresh,
    //    modifier = modifier.fillMaxWidth(),
    //) {
        /*Lazy*/Column(
            horizontalAlignment = Alignment.Start,
            //verticalArrangement = spacedBy(8.dp),
            modifier = modifier,
        ) {
            model.locations.forEach { location ->
                //item {
                    LocationHeader(
                        location = location,
                        onRenameLocation = { onRenameLocation(location.locationName) },
                        onShowLocation = { onShowLocation(location.locationName) },
                        onDeleteLocation = { onDeleteLocation(location.locationName) },
                    )
                //}
                //item {
                    LocationItem(
                        location,
                        onOpenDetail = onOpenDetail,
                    )
                //}
            }
        }
    //}
}