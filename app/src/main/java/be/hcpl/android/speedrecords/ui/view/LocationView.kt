package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel

@Composable
fun LocationView(
    model: LocationUiModel,
    settingsModel: SettingsUiModel,
    onAddNewLocation: () -> Unit,
    onRefresh: () -> Unit,
    onRefreshInfo: () -> Unit, // TODO restore pull to refresh
    onShowSettingsInfo: () -> Unit,
    onShowAppInfo: () -> Unit,
    onShowLocation: (String) -> Unit,
    onOpenDetail: (String, String, String) -> Unit,
    onChangeModel: () -> Unit,
    onChangeThreshold: () -> Unit,
    onChangeForecastDays: () -> Unit,
    onChangeUnit: () -> Unit,
    onRenameLocation: (String) -> Unit,
    onDeleteLocation: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
    ) {

        LocationOverviewHeader(
            onAddNewLocation = onAddNewLocation,
            onRefresh = onRefresh,
            onShowSettingsInfo = onShowSettingsInfo,
            onShowAppInfo = onShowAppInfo,
        )

        SettingsView(
            model = settingsModel,
            onChangeModel = onChangeModel,
            onChangeThreshold = onChangeThreshold,
            onChangeForecastDays = onChangeForecastDays,
            onChangeUnit = onChangeUnit,
            modifier = modifier.padding(8.dp),
        )

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(8.dp),
        )

        LocationOverview(
            model = model,
            onRefresh = onRefresh,
            onRenameLocation = onRenameLocation,
            onShowLocation = onShowLocation,
            onDeleteLocation = onDeleteLocation,
            onOpenDetail = onOpenDetail,
            modifier = modifier.padding(8.dp),
        )
    }
}