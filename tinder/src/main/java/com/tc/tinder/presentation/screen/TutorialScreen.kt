package com.tc.tinder.presentation.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.tinder.R
import com.tc.tinder.presentation.navigation.TinderNavHost
import com.tc.tinder.presentation.ui.button.ButtonWithTextOnly
import com.tc.tinder.presentation.ui.topbar.BackOnlyTopAppBar
import com.tc.tinder.presentation.ui.tutorial.TutorialCard


@Composable
fun TutorialScreen(
    onBackClick: () -> Unit = {},
    onDiscoverClick: () -> Unit = {}
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
                bitmap = ImageBitmap.imageResource(R.drawable.tutorial_image),
                contentDescription = "A description of the image for screen readers",
                modifier = Modifier
                    .fillMaxWidth()
                    .width(320.dp)
                    .height(280.dp)
                    .padding(end = 32.dp)
            )

            Text(
                modifier = Modifier.padding(top = 64.dp),
                text = "Tutorial",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 32.sp
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TutorialCard(
                    painterResource(R.drawable.player_fast_back_icon),
                    null,
                    "Return to Profile"
                )

                TutorialCard(
                    painterResource(R.drawable.cancel_icons),
                    null,
                    "No favorite. The profile will not appear"
                )
                TutorialCard(
                    painterResource(R.drawable.heart_light_green_icon),
                    null,
                    "Like. If it's mutual, you can talk \ntogether (5 per day)"
                )
                TutorialCard(
                    painterResource(R.drawable.star_icon),
                    null,
                    "Super like. Indicate visually that you are interested (1 per day)"
                )
                TutorialCard(
                    painterResource(R.drawable.flame_purple_icon),
                    null,
                    "Boost. Be top profile during 30 minutes."
                )
            }

            ButtonWithTextOnly("Discover the profiles", onDiscoverClick)
        }
    }
}


@Preview
@Composable
fun TinderFeatureEntrwy() {
    TutorialScreen ()
}
