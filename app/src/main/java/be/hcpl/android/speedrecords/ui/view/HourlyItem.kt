package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.domain.HourlyValue

@Composable
fun HourlyItem(
    model: HourlyValue,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
        modifier = Modifier
            .padding(4.dp)
            .border(BorderStroke(width = 1.dp, Color.LightGray))
            .padding(4.dp),
    ) {
        // TODO allow for reducing hourly range from here by dropping rows + reset option
        Text(
            text = "${model.time?.substring(11, 13)}h",
            modifier = Modifier.weight(1f),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(2.dp),
        ) {
            Text(text = "wind ${model.windSpeedAt10m} kts")
            Text(text = "gusts ${model.windGustsAt10m} kts")
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
            text = "${model.temperatureAt2m} °C",
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
@Preview
fun HourlyItemPreview() {
    HourlyItem(
        HourlyValue(
            time = "2025-12-30T12:00",
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