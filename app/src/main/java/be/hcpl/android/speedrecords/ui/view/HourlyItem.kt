package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.HourlyValueUiModel

@Composable
fun HourlyItem(
    model: HourlyValueUiModel,
    onIgnoreHour: (String) -> Unit = {},
) {

    val isHighValue = (model.windSpeedAt10m ?: 0) >= model.windThreshold

    Card(
        colors = if (!isSystemInDarkTheme()) CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        ) else CardDefaults.cardColors(),
        modifier = Modifier
            .padding(4.dp)
            .heightIn(max=64.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = spacedBy(2.dp),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 0.dp),
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
                    text = "${stringResource(R.string.label_wind)} ${model.windSpeedAt10m ?: "-"} ${stringResource(R.string.unit_knots)}",
                    fontWeight = if (isHighValue) FontWeight.Bold else FontWeight.Normal,
                    fontStyle = if (isHighValue) FontStyle.Italic else FontStyle.Normal,
                    color = if (isHighValue) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(text = "${stringResource(R.string.label_gusts)} ${model.windGustsAt10m ?: "-"} ${stringResource(R.string.unit_knots)}")
            }
            //Box(
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f),
                //modifier = Modifier.weight(1f).fillMaxHeight(),
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    modifier = Modifier
                        .rotate((model.windDirectionAt10m?.toFloat() ?: 0f) + 180)
                        .size(32.dp)
                        .padding(0.dp)
                        //.align(Alignment.TopCenter)
                )
                Text(
                    text = "${model.windDirectionAt10m}°",
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        //.align(Alignment.BottomCenter)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
            ) {
                Text(text = "${stringResource(R.string.label_cover)} ${model.cloudCover}%")
                Text(text = "${model.precipitation} mm")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f)
                    //.fillMaxHeight(),
            ) {
                model.weatherIcon?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = model.weatherDescription,
                        modifier = Modifier.size(32.dp)
                            .padding(0.dp)
                                //.align(Alignment.TopCenter)
                    )
                }
                Text(
                    text = model.temperature,
                    maxLines = 1,
                    modifier = Modifier
                        //.align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp)
                )
            }
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
        HourlyValueUiModel(
            time = "2025-12-30T12:00",
            displayTime = "12",
            temperature = "16 °C",
            precipitation = 0.2,
            cloudCover = 66,
            windSpeedAt10m = 10,
            windDirectionAt10m = 180,
            windGustsAt10m = 12,
            weatherIcon = R.drawable.wmo_02d,
            weatherDescription = "weather description",
        )
    )
}

@Composable
@Preview
fun HourlyItemPreviewLowValue() {
    HourlyItem(
        HourlyValueUiModel(
            time = "2025-12-30T12:00",
            displayTime = "12",
            temperature = "20°C",
            precipitation = 0.2,
            cloudCover = 66,
            windSpeedAt10m = 9,
            windDirectionAt10m = 40,
            windGustsAt10m = 12,
            weatherIcon = R.drawable.wmo_10d,
            weatherDescription = "weather description",
        )
    )
}

@Composable
@Preview(locale = "nl")
fun HourlyItemPreviewLowValueDutch300Device() {
    Box(modifier = Modifier.width(300.dp)) {
        HourlyItem(
            HourlyValueUiModel(
                time = "2025-12-30T12:00",
                displayTime = "12",
                temperature = "20°C",
                precipitation = 0.2,
                cloudCover = 100,
                windSpeedAt10m = 9,
                windDirectionAt10m = 40,
                windGustsAt10m = 12,
                weatherIcon = R.drawable.wmo_10d,
                weatherDescription = "weather description",
            )
        )
    }
}