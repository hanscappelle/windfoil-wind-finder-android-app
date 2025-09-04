package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NameLocationDialog(
    openDialog: MutableState<Boolean>,
    locationName: MutableState<String>,
    onNameEntered: (String) -> Unit = {},
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
                    Text(
                        text = stringResource(R.string.info_rename_location),
                        fontSize = 20.sp,
                    )
                    OutlinedTextField(
                        value = locationName.value,
                        onValueChange = { locationName.value = it },
                        label = { stringResource(R.string.name) },
                        //placeholder = { Text("John Doe") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                    Button(
                        onClick = {
                            onNameEntered(locationName.toString())
                            openDialog.value = false
                        },
                    ) {
                        Text(text = stringResource(R.string.ok))
                    }
                }
            }
        }
    }
}