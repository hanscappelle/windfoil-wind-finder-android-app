package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel

@Composable
fun SettingsView(
    model: SettingsUiModel,
) {
    Column(Modifier
        .fillMaxWidth()) {
        // Table header with some background
        Row(Modifier.background(Color.Gray)) {
            TableCell(text = stringResource(R.string.label_weather_model), weight = 1f)
            TableCell(text = stringResource(R.string.label_threshold), weight = 0.3f)
            TableCell(text = stringResource(R.string.label_number_of_days), weight = 0.3f)
            TableCell(text = stringResource(R.string.label_temperature_unit), weight = 0.3f)
        }
        // table content
        Row(Modifier.fillMaxWidth()) {
            TableCell(text = "${model.source.name}/${model.source.model.name}", weight = 1f)
            TableCell(text = "${model.threshold} kts", weight = 0.3f)
            TableCell(text = "${model.days} days", weight = 0.3f)
            TableCell(text = model.unit.name, weight = 0.3f)
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
) {
    Text(
        text = text,
        Modifier
            //.border(1.dp, Color.Black)
            .weight(weight)
            //.padding(8.dp)
    )
}

@Preview
@Composable
fun SettingsViewPreview() {
    SettingsView(
        model = SettingsUiModel(),
    )
}