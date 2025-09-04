package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoDialog(openDialog: MutableState<Boolean>) {

    if (openDialog.value) {
        androidx.compose.material3.AlertDialog(
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
                    Text(
                        text = stringResource(R.string.info_add_location),
                        fontSize = 20.sp,
                    )
                    Text(
                        text = stringResource(R.string.info_add_location_extra),
                        fontSize = 20.sp,
                    )
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