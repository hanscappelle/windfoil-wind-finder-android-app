package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R

@Composable
fun LocationOverviewHeader(
    onAddNewLocation: () -> Unit,
    onRefresh: () -> Unit = {},
    onShowSettingsInfo: () -> Unit = {},
    onShowAppInfo: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
        modifier = Modifier.background(
            MaterialTheme.colorScheme.primaryContainer,
        ).padding(8.dp)
    ) {

        Text(
            text = stringResource(id = R.string.title_favourite_locations),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.a11y_add_location),
            modifier = Modifier.clickable(onClick = onAddNewLocation)
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = stringResource(id = R.string.a11y_update_data),
            modifier = Modifier.clickable(onClick = onRefresh)
        )
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.a11y_access_settings),
            modifier = Modifier.clickable(onClick = onShowSettingsInfo)
        )
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = stringResource(id = R.string.a11y_show_app_info),
            modifier = Modifier.clickable(onClick = onShowAppInfo)
        )
        //    imageVector = Icons.Default.Build,
        //imageVector = Icons.Default.DateRange,
    }
}