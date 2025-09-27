package be.hcpl.android.speedrecords.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.domain.model.ModelType
import be.hcpl.android.speedrecords.ui.dialog.ConfirmDialog
import be.hcpl.android.speedrecords.ui.dialog.InfoDialog
import be.hcpl.android.speedrecords.ui.dialog.InfoDialogUiModel
import be.hcpl.android.speedrecords.ui.dialog.NameLocationDialog
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel
import be.hcpl.android.speedrecords.ui.view.LocationView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: List<LocationUiModel>,
    settingsModel: List<SettingsUiModel>,
    refreshing: Boolean,
    onRefresh: (ModelType) -> Unit = {},
    onAddLocation: () -> Unit = {},
    onUpdateLocationName: (String, String) -> Unit = { _, _ -> },
    onShowLocation: (String) -> Unit = {},
    onDeleteLocation: (String) -> Unit = {},
    onOpenDetail: (String, ModelType, String, String) -> Unit,
    onChangeModel: (ModelType) -> Unit = {},
    onChangeThreshold: () -> Unit = {},
    onChangeForecastDays: () -> Unit = {},
    onChangeUnit: () -> Unit = {},
) {
    // some dialogs
    val openInfoDialog = remember { mutableStateOf(false) }
    val infoDialogModel = remember { mutableStateOf(InfoDialogUiModel.locationInfo) }
    InfoDialog(openInfoDialog, infoDialogModel.value)

    val addLocationDialog = remember { mutableStateOf(false) }
    val locationNameState = remember { mutableStateOf("") }
    // TODO should work with an ID instead...
    val oldNameValueState = remember { mutableStateOf("") }
    NameLocationDialog(
        openDialog = addLocationDialog,
        locationName = locationNameState,
        onNameEntered = { newName -> onUpdateLocationName(oldNameValueState.value, locationNameState.value) }
    )

    val confirmDialog = remember { mutableStateOf(false) }
    ConfirmDialog(
        confirmDialog,
        message = stringResource(R.string.confirm_delete),
        onConfirm = { onDeleteLocation(oldNameValueState.value) },
        onCancel = {},
    )

    // content
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val noRows = if (isLandscape) 2 else 1

    PullToRefreshBox(
        isRefreshing = refreshing, //model[0].isRefreshing,// only using 1 model for refresh state || model[1].isRefreshing,
        onRefresh = {
            onRefresh(ModelType.MAIN)
            onRefresh(ModelType.ALT)
        },
        modifier = modifier.fillMaxWidth(),
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(noRows),
            //verticalArrangement = spacedBy(4.dp),
            //modifier = modifier.padding(8.dp),
        ) {

            item {
                LocationView(
                    modifier = modifier,
                    settingsModel = settingsModel[0],
                    model = model[0],
                    onAddNewLocation = {
                        openInfoDialog.value = true
                        infoDialogModel.value = InfoDialogUiModel.locationInfo
                        onAddLocation()
                    },
                    //onRefresh = { onRefresh(ModelType.MAIN) },
                    onRefreshInfo = {
                        openInfoDialog.value = true
                        infoDialogModel.value = InfoDialogUiModel.refreshInfo
                    },
                    onShowSettingsInfo = {
                        openInfoDialog.value = true
                        infoDialogModel.value = InfoDialogUiModel.settingsInfo
                    },
                    onShowAppInfo = {
                        openInfoDialog.value = true
                        infoDialogModel.value = InfoDialogUiModel.appInfo
                    },
                    onRenameLocation = { name ->
                        locationNameState.value = name
                        oldNameValueState.value = name
                        addLocationDialog.value = true
                    },
                    onDeleteLocation = { name ->
                        oldNameValueState.value = name
                        confirmDialog.value = true
                    },
                    onShowLocation = onShowLocation,
                    onOpenDetail = { name, time, day -> onOpenDetail(name, ModelType.MAIN, time, day) },
                    onChangeModel = { onChangeModel(ModelType.MAIN) },
                    onChangeThreshold = onChangeThreshold,
                    onChangeForecastDays = onChangeForecastDays,
                    onChangeUnit = onChangeUnit,
                )
            }

            if (isLandscape) {
                item {
                    LocationView(
                        modifier = modifier,
                        model = model[1],
                        settingsModel = settingsModel[1],
                        onAddNewLocation = {
                            openInfoDialog.value = true
                            infoDialogModel.value = InfoDialogUiModel.locationInfo
                        },
                        //onRefresh = { onRefresh(ModelType.ALT) },
                        onRefreshInfo = {
                            openInfoDialog.value = true
                            infoDialogModel.value = InfoDialogUiModel.refreshInfo
                        },
                        onShowSettingsInfo = {
                            openInfoDialog.value = true
                            infoDialogModel.value = InfoDialogUiModel.settingsInfo
                        },
                        onShowAppInfo = {
                            openInfoDialog.value = true
                            infoDialogModel.value = InfoDialogUiModel.appInfo
                        },
                        onRenameLocation = { name ->
                            locationNameState.value = name
                            oldNameValueState.value = name
                            addLocationDialog.value = true
                        },
                        onDeleteLocation = { name ->
                            oldNameValueState.value = name
                            confirmDialog.value = true
                        },
                        onShowLocation = onShowLocation,
                        onOpenDetail = { name, time, day -> onOpenDetail(name, ModelType.ALT, time, day) },
                        onChangeModel = { onChangeModel(ModelType.ALT) },
                        onChangeThreshold = onChangeThreshold,
                        onChangeForecastDays = onChangeForecastDays,
                        onChangeUnit = onChangeUnit,
                    )
                }
            }
        }

    }
}