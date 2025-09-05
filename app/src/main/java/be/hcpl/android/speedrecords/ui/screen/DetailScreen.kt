package be.hcpl.android.speedrecords.ui.screen

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel
import be.hcpl.android.speedrecords.ui.view.HourlyHeader
import be.hcpl.android.speedrecords.ui.view.HourlyItem

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    model: HourlyUiModel,
    onRestoreAllHours: () -> Unit = {},
    onIgnoreHour: (String) -> Unit = {},
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = spacedBy(8.dp),
        modifier = modifier.padding(8.dp),
    ) {

        HourlyHeader(
            model = model,
            onRestoreAllHours = onRestoreAllHours,
        )

        HorizontalDivider(
            color = Color.White,
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )

        LazyColumn(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = spacedBy(8.dp),
        ) {
            model.hourly.forEach { hour, data ->
                item {
                    HourlyItem(
                        model = data,
                        onIgnoreHour = onIgnoreHour,
                    )
                }
            }
        }
    }

}
