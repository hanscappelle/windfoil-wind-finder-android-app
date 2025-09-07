package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.domain.DailyValue
import be.hcpl.android.speedrecords.domain.WeatherData
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import kotlin.math.roundToInt

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
                // display min and max wind speeds
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(2.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "min ${it.value.windSpeedAt10mMin?.roundToInt() ?: "-"} kts")
                    Text(text = "max ${it.value.windSpeedAt10mMax?.roundToInt() ?: "-"} kts")
                }
                // display min and max temperatures
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = spacedBy(2.dp),
                    modifier = Modifier.weight(1f),
                ) {
                    Text(text = "min ${it.value.temperatureAt2mMin?.roundToInt() ?: "-"} °C")
                    Text(text = "max ${it.value.temperatureAt2mMax?.roundToInt() ?: "-"} °C")
                }
            }
        }
    }
}

@Composable
@Preview
fun LocationItemPreview(){
    LocationItem(

    model= LocationItemUiModel(
        locationName = "Brussels",
        lat = 0.0,
        lng = 0.0,
        hourlyForecast = WeatherData(
            latitude = 0.0,
            longitude = 0.0,
            timezone = "GMT",
            units = null,
            daily = mapOf("sqdsqd" to DailyValue(
                time = "sqdsqd",
                displayDay = "10h",
                windSpeedAt10mMin = 11.0,
                windSpeedAt10mMax = 16.0,
                temperatureAt2mMin = 18.0,
                temperatureAt2mMax = 22.0,
                windGustsAt10mMax = 20.0,

            )),
            hourly = mapOf(),
        ),
    ),
    )
}