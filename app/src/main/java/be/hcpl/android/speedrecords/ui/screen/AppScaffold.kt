package be.hcpl.android.speedrecords.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.hcpl.android.speedrecords.R
import be.hcpl.android.speedrecords.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AppScaffold(
    title: String = stringResource(R.string.app_name),
    onBack: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {

    // for landscape keep a minimal topBar
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = if (!isLandscape) TopAppBarDefaults.topAppBarColors(
                        // needed for the top image to show through
                        containerColor = Color.Transparent,
                        titleContentColor = Color.Black,
                        navigationIconContentColor = Color.Black,
                        actionIconContentColor = Color.Black,
                    ) else TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        navigationIconContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        actionIconContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    ),
                    title = { Text(text = title, fontWeight = FontWeight.Bold) },
                    modifier = if (!isLandscape) Modifier.paint(
                        painter = painterResource(R.drawable.play_store_512),
                        contentScale = ContentScale.FillBounds,
                    ) else if (onBack != null) {
                        Modifier.height(64.dp)// have minimal room for back arrow
                    } else {
                        Modifier.height(24.dp)
                    },
                    navigationIcon = {
                        onBack?.let {
                            IconButton(onClick = { onBack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = stringResource(R.string.a11y_navigate_back)
                                )
                            }
                        }
                    },
                )
            }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
fun AppScaffoldPreview() {
    AppScaffold { }
}