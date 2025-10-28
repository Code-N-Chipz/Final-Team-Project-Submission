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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.tinder.R
import com.tc.tinder.presentation.ui.button.ButtonWithTextOnly
import com.tc.tinder.presentation.ui.topbar.BackOnlyTopAppBar
import theme.ICLICKIPAYTheme

@Composable
fun StartingScreen(
    onBackClick: () -> Unit = {},
    onLetsGoClick: () -> Unit = {}
) {
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = { BackOnlyTopAppBar(onBackClick) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
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
                    painter = painterResource(id = R.drawable.meet_page_image),
                    contentDescription = "A description of the image for screen readers",
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .padding(horizontal = 12.dp),
                    text = "Meet",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 32.dp)
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 18.sp,
                    color = Color(0xFFC4C8CF),
                    textAlign = TextAlign.Center
                )

                // assumes ButtonWithTextAndIcon(text, painter, onClick) exists
               ButtonWithTextOnly("Lets Go",onLetsGoClick)


            }
        }
    }}

@Preview
@Composable
fun prev(){
    ICLICKIPAYTheme {
        StartingScreen({},{})
    }
}

