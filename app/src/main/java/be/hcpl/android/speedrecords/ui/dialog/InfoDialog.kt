package be.hcpl.android.speedrecords.ui.dialog

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoDialog(
    openDialog: MutableState<Boolean>,
    uiModel: InfoDialogUiModel,
) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(8.dp),
                    modifier = Modifier
                        .padding(32.dp)

                ) {
                    uiModel.infoText.forEach {
                        Text(
                            text = stringResource(it),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = { openDialog.value = false },
                    ) {
                        Text(text = stringResource(R.string.ok))
                    }
                }
            }
        }
    }
}

data class InfoDialogUiModel(
    val infoText: List<Int>,
) {


    companion object {

        val locationInfo = InfoDialogUiModel(
            infoText = listOf(
                R.string.info_add_location,
                R.string.info_add_location_extra,
            )
        )

        val settingsInfo = InfoDialogUiModel(
            infoText = listOf(
                R.string.info_settings,
                R.string.info_settings_more,
            )
        )

        val refreshInfo = InfoDialogUiModel(
            infoText = listOf(
                R.string.info_refresh,
                R.string.info_refresh_more,
            )
        )
    }
}
