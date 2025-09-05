package be.hcpl.android.speedrecords.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.model.LocationItemUiModel

@Composable
fun LocationHeader(
    location: LocationItemUiModel,
    onShowLocation: () -> Unit = {},
    onRenameLocation: (String) -> Unit = {},
    onDeleteLocation: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(4.dp),
        modifier = Modifier.heightIn(min = 48.dp)
    ) {
        Text(
            text = location.locationName,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = onShowLocation)
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = stringResource(id = R.string.a11y_edit_location),
            modifier = Modifier.clickable(onClick = { onRenameLocation(location.locationName) })
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(id = R.string.a11y_delete_location),
            modifier = Modifier.clickable(onClick = onDeleteLocation)
        )
    }
}