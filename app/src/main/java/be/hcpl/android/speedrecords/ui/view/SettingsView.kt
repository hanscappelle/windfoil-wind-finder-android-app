package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel

@Composable
fun SettingsView(
    model: SettingsUiModel,
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
    ){
        Text(text = "Unit: ${model.unit.name}")
        Text(text = "Model: ${model.source.name}/${model.source.model.name}")
    }

}

@Preview
@Composable
fun SettingsViewPreview() {
    SettingsView(
        model = SettingsUiModel(),
    )
}