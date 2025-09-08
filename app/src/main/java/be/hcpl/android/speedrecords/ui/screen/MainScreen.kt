package be.hcpl.android.speedrecords.ui.screen

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.dialog.ConfirmDialog
import be.hcpl.android.speedrecords.ui.dialog.InfoDialog
import be.hcpl.android.speedrecords.ui.dialog.NameLocationDialog
import be.hcpl.android.speedrecords.ui.model.LocationUiModel
import be.hcpl.android.speedrecords.ui.view.LocationOverview
import be.hcpl.android.speedrecords.ui.view.LocationsOverviewHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    model: LocationUiModel,
    onRefresh: () -> Unit = {},
    onUpdateLocationName: (String, String) -> Unit = { _, _ -> },
    onShowLocation: (String) -> Unit = {},
    onDeleteLocation: (String) -> Unit = {},
    onOpenDetail: (String, String, String) -> Unit = { _, _, _ -> },
    onChangeUnit: () -> Unit = {},
) {
    // some dialogs
    val openInfoDialog = remember { mutableStateOf(false) }
    InfoDialog(openInfoDialog)

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
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
        modifier = modifier.padding(8.dp),
    ) {

        LocationsOverviewHeader(
            onAddNewLocation = { openInfoDialog.value = true },
            onRefresh = onRefresh,
            onChangeUnit = onChangeUnit,
        )

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )

        LocationOverview(
            model = model,
            onRefresh = onRefresh,
            onRenameLocation = { name ->
                locationNameState.value = name
                oldNameValueState.value = name
                addLocationDialog.value = true
            },
            onShowLocation = onShowLocation,
            onDeleteLocation = { name ->
                oldNameValueState.value = name
                confirmDialog.value = true
            },
            onOpenDetail = onOpenDetail,
        )
    }

}