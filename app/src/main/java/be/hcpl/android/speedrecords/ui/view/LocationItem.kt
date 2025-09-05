package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel
import kotlin.math.roundToInt

@Composable
fun LocationItem(
    model: LocationItemUiModel,
    onOpenDetail: (String, String) -> Unit = { _, _ -> },
) {

    model.hourlyForecast?.daily?.forEach {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(8.dp),
            modifier = Modifier
                .clickable { onOpenDetail(model.locationName, it.value.time.orEmpty()) }
                .padding(4.dp)
                .border(BorderStroke(width = 1.dp, Color.LightGray))
                .padding(4.dp)
            ,
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