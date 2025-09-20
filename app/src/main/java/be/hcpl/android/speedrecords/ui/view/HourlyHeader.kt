package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.HourlyUiModel

@Composable
fun HourlyHeader(
    model: HourlyUiModel,
    onRestoreAllHours: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
        modifier = Modifier
            .heightIn(min = 48.dp)
            .background(
                if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primaryContainer,
            )
            .padding(8.dp)
    ) {
        Text(
            text = "${model.locationName} - ${model.date} - ${model.day}",
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1f)
            //TODO from here also navigation to map?
            //.clickable(onClick = onShowLocation)
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = stringResource(id = R.string.a11y_restore_all_hours),
            modifier = Modifier.clickable(onClick = onRestoreAllHours)
        )
    }
}

@Composable
@Preview
fun HourlyHeaderPreview() {
    HourlyHeader(
        model = HourlyUiModel(
            locationName = "Brussels",
            date = "2025-08-27",
            day = "Sunday",
            hourly = emptyMap(),
        )
    )
}