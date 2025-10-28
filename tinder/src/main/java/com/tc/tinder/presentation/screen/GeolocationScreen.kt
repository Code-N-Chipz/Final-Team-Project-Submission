package com.tc.tinder.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.tinder.R
import com.tc.tinder.presentation.ui.button.ButtonWithTextOnly
import com.tc.tinder.presentation.ui.topbar.BackOnlyTopAppBar


@Composable
fun GeolocationScreen(
    onBackClick: () -> Unit = {},
    onActivateClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { BackOnlyTopAppBar(onBackClick = onBackClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.geolocation_image),
                contentDescription = "A description of the image for screen readers",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                modifier = Modifier.padding(top = 64.dp),
                text = "Enable geolocation",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 32.sp
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 16.dp),
                text = "To propose profiles near you,\n" +
                        "you must activate the localization",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFC4C8CF),
                textAlign = TextAlign.Center
            )

            ButtonWithTextOnly("Activate", onActivateClick)
        }
    }
}
