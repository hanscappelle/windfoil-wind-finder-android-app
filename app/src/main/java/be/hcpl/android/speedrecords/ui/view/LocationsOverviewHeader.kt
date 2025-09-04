package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R

@Composable
fun LocationsOverviewHeader(
    onAddNewLocation: () -> Unit,
    onRefresh: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
    ) {

        Text(
            text = stringResource(id = R.string.title_favourite_locations),
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(android.R.drawable.ic_menu_add),
            contentDescription = stringResource(id = R.string.a11y_add_location),
            modifier = Modifier.clickable(onClick = onAddNewLocation)
        )
        Icon(
            painter = painterResource(android.R.drawable.ic_menu_rotate),
            contentDescription = stringResource(id = R.string.a11y_update_data),
            modifier = Modifier.clickable(onClick = onRefresh)
        )
    }
}