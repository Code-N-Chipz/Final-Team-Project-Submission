package com.tc.tinder.view.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.tinder.R
import theme.ICLICKIPAYTheme
import theme.primaryColor


@Composable
fun TinderMainScreen() {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) { }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetScreen(
    onBackClick: () -> Unit = {},
    onLetGoClick: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {}, // no title
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = com.tc.design.R.drawable.arrow_left_orange_icon),
                            contentDescription = "Back Arrow",
                            tint = Color.Unspecified
                        )
                    }
                }
            )
        }
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
                painter = painterResource(id = R.drawable.meet_page_image),
                contentDescription = "A description of the image for screen readers",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                modifier = Modifier.padding(top = 64.dp),
                text = "Meet",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 32.sp
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 16.dp),
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFC4C8CF),
                textAlign = TextAlign.Center
            )


            ButtonWithTextOnly("Let's Go", onLetGoClick)

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PictureScreen(
    onBackClick: () -> Unit = {},
    onAddPictureClick: () -> Unit = {},
    onTakePicture: () -> Unit = {}

) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {}, // no title
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = com.tc.design.R.drawable.arrow_left_orange_icon),
                            contentDescription = "Back Arrow",
                            tint = Color.Unspecified
                        )
                    }
                }
            )
        }
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
                painter = painterResource(id = R.drawable.add_profile_picture_image),
                contentDescription = "A description of the image for screen readers",
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .padding(horizontal = 12.dp),
                text = "Add your profile picture",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                text = "Add photo to personalize your space.",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                color = Color(0xFFC4C8CF),
                textAlign = TextAlign.Center
            )



            ButtonWithTextAndIcon(
                "Add a Picture",
                painterResource(com.tc.design.R.drawable.image_icon),
                onAddPictureClick
            )


            ButtonWithTextAndIcon(
                "Take a Picture",
                painterResource(com.tc.design.R.drawable.camera_icon),
                onAddPictureClick
            )


        }
    }
}


@Composable
fun ButtonWithTextOnly(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 64.dp)
            .padding(horizontal = 32.dp)
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ButtonWithTextAndIcon(
    text: String,
    iconPainter: Painter,
    onClick: () -> Unit,
    contentDescription: String? = null,
    modifier: Modifier = Modifier

) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 32.dp)
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 28.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = contentDescription,
                modifier = Modifier.padding(end = 16.dp),
                tint = Color.Unspecified
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MeetScreenPreview() {
    ICLICKIPAYTheme() {

        PictureScreen()

    }
}
