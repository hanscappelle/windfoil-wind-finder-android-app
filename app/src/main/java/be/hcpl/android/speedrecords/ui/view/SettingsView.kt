package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.SettingsUiModel

@Composable
fun SettingsView(
    model: SettingsUiModel,
    modifier: Modifier = Modifier,
    onChangeModel: () -> Unit = {},
    onChangeThreshold: () -> Unit = {},
    onChangeForecastDays: () -> Unit = {},
    onChangeUnit: () -> Unit = {},
) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        // Table header with some background
        Row(Modifier.background(MaterialTheme.colorScheme.surfaceContainer)) {
            TableCell(
                text = stringResource(R.string.label_weather_model),
                modifier = Modifier
                    .weight(1f)
                    .clickable { onChangeModel() },
            )
            TableCell(
                text = stringResource(R.string.label_threshold),
                modifier = Modifier
                    .weight(0.3f)
                    .clickable { onChangeThreshold() },
            )
            TableCell(
                text = stringResource(R.string.label_number_of_days),
                modifier = Modifier
                    .weight(0.3f)
                    .clickable { onChangeForecastDays() },
            )
            TableCell(
                text = stringResource(R.string.label_temperature_unit),
                modifier = Modifier
                    .weight(0.3f)
                    .clickable { onChangeUnit() },
            )
        }
        // table content
        Row(Modifier.fillMaxWidth()) {
            TableCell(
                text = "${model.source.name}/${model.source.model.name}/${model.source.model.origin}/${model.source.model.resolutionMin}-${model.source.model.resolutionMax}km",
                modifier = Modifier
                    .weight(1f)
                    .clickable { onChangeModel() },
            )
            TableCell(
                text = "${model.threshold} ${stringResource(R.string.unit_knots)}",
                modifier = Modifier
                    .weight(0.3f)
                    .clickable { onChangeThreshold() },
            )
            TableCell(
                text = "${model.forecastDays} ${stringResource(R.string.unit_days)}",
                modifier = Modifier
                    .weight(0.3f)
                    .clickable { onChangeForecastDays() },
            )
            TableCell(
                text = model.unit.name,
                modifier = Modifier
                    .weight(0.3f)
                    .clickable { onChangeUnit() },
            )
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    modifier: Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
    )
}

@Preview
@Composable
fun SettingsViewPreview() {
    SettingsView(
        model = SettingsUiModel(),
    )
}