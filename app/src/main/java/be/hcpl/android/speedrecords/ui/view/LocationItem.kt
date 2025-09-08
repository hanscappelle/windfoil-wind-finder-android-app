package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.DailyValueUiModel
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import be.hcpl.android.speedrecords.ui.model.WeatherDataUiModel

@Composable
fun LocationItem(
    model: LocationItemUiModel,
    onOpenDetail: (String, String) -> Unit = { _, _ -> },
) {

    model.hourlyForecast?.daily?.forEach {
        Card(
            modifier = Modifier.padding(4.dp),
            onClick = { onOpenDetail(model.locationName, it.value.time.orEmpty()) },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = spacedBy(8.dp),
                modifier = Modifier.padding(4.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(2.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "${it.value.time}")
                    Text(text = "${it.value.displayDay}")
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    modifier = Modifier.rotate(it.value.windDirectionAt10m?.toFloat() ?: 0f)
                )
                // display min and max wind speeds
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(2.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = "min ${it.value.windSpeedAt10mMin ?: "-"} kts",
                        fontWeight = if ((it.value.windSpeedAt10mMin ?: 0) >= 10) FontWeight.Bold else FontWeight.Normal,
                        fontStyle = if ((it.value.windSpeedAt10mMin ?: 0) >= 10) FontStyle.Italic else FontStyle.Normal,
                    )
                    Text(
                        text = "max ${it.value.windSpeedAt10mMax ?: "-"} kts",
                        fontWeight = if ((it.value.windSpeedAt10mMax ?: 0) >= 10) FontWeight.Bold else FontWeight.Normal,
                        fontStyle = if ((it.value.windSpeedAt10mMax ?: 0) >= 10) FontStyle.Italic else FontStyle.Normal,
                    )
                }
                it.value.weatherIcon?.let { icon ->
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = it.value.weatherDescription,
                        modifier = Modifier.size(32.dp)
                    )
                }
                // display min and max temperatures
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(2.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "min ${it.value.tempMin}")
                    Text(text = "max ${it.value.tempMax}")
                }
            }
        }
    }
}

@Composable
@Preview
fun LocationItemPreview() {
    LocationItem(

        model = LocationItemUiModel(
            locationName = "Brussels",
            lat = 0.0,
            lng = 0.0,
            hourlyForecast = WeatherDataUiModel(
                latitude = 0.0,
                longitude = 0.0,
                daily = mapOf(
                    "date" to DailyValueUiModel(
                        time = "date",
                        displayDay = "10h",
                        windSpeedAt10mMin = 11,
                        windSpeedAt10mMax = 16,
                        tempMin = "18 °C",
                        tempMax = "22 °C",
                        weatherIcon = R.drawable.wmo_02d,
                        weatherDescription = "weather description",
                        windDirectionAt10m = 100,
                    )
                ),
            ),
        ),
    )
}