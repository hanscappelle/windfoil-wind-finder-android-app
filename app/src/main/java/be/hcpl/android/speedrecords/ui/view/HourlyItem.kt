package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.domain.HourlyValue
import kotlin.math.roundToInt

@Composable
fun HourlyItem(
    model: HourlyValue,
    onIgnoreHour: (String) -> Unit = {},
) {

    Card(
        modifier = Modifier.padding(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(8.dp),
            modifier = Modifier.padding(4.dp),
        ) {
            Text(
                text = "${model.displayTime}h",
                modifier = Modifier.weight(1f),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
            ) {
                Text(
                    text = "wind ${model.windSpeedAt10m?.roundToInt() ?: "-"} kts",
                    // TODO move this logic to transformer!?
                    fontWeight = if ((model.windSpeedAt10m ?: 0.0) >= 10) FontWeight.Bold else FontWeight.Normal,
                    fontStyle = if ((model.windSpeedAt10m ?: 0.0) >= 10) FontStyle.Italic else FontStyle.Normal,
                )
                Text(text = "gusts ${model.windGustsAt10m?.roundToInt() ?: "-"} kts")
            }
            Text(
                text = "${model.windDirectionAt10m} °",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
            ) {
                Text(text = "cover ${model.cloudCover} %")
                Text(text = "${model.precipitation} mm")
            }
            Text(
                text = "${model.temperatureAt2m?.roundToInt() ?: "-"} °C",
                modifier = Modifier.weight(1f),
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.a11y_hide_this_hour),
                modifier = Modifier.clickable(onClick = { onIgnoreHour(model.time.orEmpty()) })
            )
        }
    }
}

@Composable
@Preview
fun HourlyItemPreviewMarked() {
    HourlyItem(
        HourlyValue(
            time = "2025-12-30T12:00",
            displayTime = "12",
            temperatureAt2m = 16.0,
            precipitation = 0.2,
            weatherCode = 2,
            cloudCover = 66,
            windSpeedAt10m = 10.0,
            windDirectionAt10m = 180,
            windGustsAt10m = 12.4,
        )
    )
}

@Composable
@Preview
fun HourlyItemPreviewLowValue() {
    HourlyItem(
        HourlyValue(
            time = "2025-12-30T12:00",
            displayTime = "12",
            temperatureAt2m = 16.0,
            precipitation = 0.2,
            weatherCode = 2,
            cloudCover = 66,
            windSpeedAt10m = 9.0,
            windDirectionAt10m = 180,
            windGustsAt10m = 12.4,
        )
    )
}