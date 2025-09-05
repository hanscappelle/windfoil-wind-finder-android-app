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
import be.hcpl.android.speedrecords.domain.HourlyValue
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel

@Composable
fun HourlyItem(
    model: HourlyValue,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .border(BorderStroke(width = 1.dp, Color.LightGray)),
    ) {
        // hour indication
        // TODO allow for reducing hourly range here
        Text(
            text = "${model.time?.substring(11,13)}h",
            //modifier = Modifier.weight(1f),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(2.dp),
            modifier = Modifier.weight(1f),
        ) {
            Text(text = "wind ${model.windSpeedAt10m} knots")
            Text(text = "gusts ${model.windGustsAt10m} knots")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(2.dp),
            modifier = Modifier.weight(1f),
        ) {
            Text(text = "cover ${model.cloudCover} %")
            Text(text = "${model.precipitation} mm")
        }
        Text(
            text = "${model.temperatureAt2m} °C"
        )
    }
}