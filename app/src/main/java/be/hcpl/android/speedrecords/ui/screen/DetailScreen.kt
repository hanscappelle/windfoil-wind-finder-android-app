package be.hcpl.android.speedrecords.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.dialog.ConfirmDialog
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.view.HourlyHeader
import be.hcpl.android.speedrecords.ui.view.HourlyItem

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    model: HourlyUiModel,
    onRestoreAllHours: () -> Unit = {},
    onIgnoreHour: (String) -> Unit = {},
) {

    val confirmDialog = remember { mutableStateOf(false) }
    ConfirmDialog(
        confirmDialog,
        message = stringResource(R.string.confirm_restore_all_hours),
        onConfirm = { onRestoreAllHours() },
        onCancel = {},
    )

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
        modifier = modifier,
    ) {

        HourlyHeader(
            model = model,
            onRestoreAllHours = { confirmDialog.value = true },
        )

        val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        val noRows = if (isLandscape) 2 else 1

        LazyVerticalGrid(
            columns = GridCells.Fixed(noRows),
            verticalArrangement = spacedBy(4.dp),
            modifier = modifier.padding(8.dp),
        ) {
            model.hourly.forEach { hour, data ->
                item {
                    HourlyItem(
                        model = data,
                        onIgnoreHour = onIgnoreHour,
                    )
                }
            }
        }
    }

}
