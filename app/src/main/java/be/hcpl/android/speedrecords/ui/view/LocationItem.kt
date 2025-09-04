package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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

@Composable
fun LocationItem(model: LocationItemUiModel) {
    model.hourlyForecast?.daily?.forEach {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(8.dp),
            modifier = Modifier
                .padding(4.dp)
                .border(BorderStroke(width = 1.dp, Color.LightGray)),
        ) {
            // TODO include day of week
            Text(
                text = "${it.value.time}",
                modifier = Modifier.weight(1f),
            )
            // display min and max wind speeds
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "min ${it.value.windSpeedAt10mMin} knots")
                Text(text = "max ${it.value.windSpeedAt10mMax} knots")
            }
            // display min and max temperatures
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = spacedBy(2.dp),
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "min ${it.value.temperatureAt2mMin} °C")
                Text(text = "max ${it.value.temperatureAt2mMax} °C")
            }
        }
    }
}