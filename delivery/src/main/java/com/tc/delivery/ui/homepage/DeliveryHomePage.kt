package com.tc.delivery.ui.homepage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tc.delivery.ui.comon.TopBar

@Composable
fun DeliveryHomePage(
    modifier: Modifier = Modifier,
    onExitLaundry: () -> Unit = {},
    navController: NavController
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val overlayHeight = 400.dp

    Box(modifier = modifier.fillMaxSize()) {
        // Main Column for top and bottom backgrounds
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top background image
            Box(
                modifier = Modifier
                    .height(screenHeight / 2)
                    .fillMaxWidth()
            ) {

                TopBar(
                    icon = com.tc.design.R.drawable.home_icon,
                    onClick = onExitLaundry,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth()
                        .height(56.dp)
                )
            }

            // Second background
            SecondBackground(
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(22.dp))

            ThirdBackground(
                navController = navController
            )
        }

        // Overlay Box floating above both backgrounds
        OverlayerBox(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (screenHeight / 2) - (overlayHeight / 2)), // middle between top & bottom backgrounds
            navController = navController
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LaundryHomePagePreview(){
    DeliveryHomePage(navController = rememberNavController())
}