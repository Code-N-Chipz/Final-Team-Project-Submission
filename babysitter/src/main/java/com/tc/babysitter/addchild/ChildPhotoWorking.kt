package com.tc.babysitter.addchild

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tc.ui.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildPhoto1(
    navController: NavHostController
) {
    var photoSelected by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Child Photo",
                        fontFamily = PoppinsFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = DarkText
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = DarkText
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            BottomBarButton_new(
                enabled = photoSelected,
                text = "CONTINUE",
                onClick = {  }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Circular photo card (click to select)
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .border(
                        width = 3.dp,
                        color = if (photoSelected) PrimaryOrange else GrayText.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                    .background(Color(0xFFF5F5F5))
                    .clickable {
                        // In real app: open gallery or camera picker
                        photoSelected = !photoSelected
                    },
                contentAlignment = Alignment.Center
            ) {
                if (photoSelected) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Selected",
                        tint = PrimaryOrange,
                        modifier = Modifier.size(48.dp)
                    )
                } else {
                    // placeholder image
                    Image(
                        painter = painterResource(R.drawable.babysitter_homepage_image), // Replace with your own placeholder
                        contentDescription = "Placeholder",
                        modifier = Modifier.size(80.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = if (photoSelected) "Photo Selected" else "Upload Child Photo",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = DarkText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tap the circle to upload your child’s photo.\nYou can use camera or gallery.",
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = GrayText,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun BottomBarButton_new(enabled: Boolean, text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = text,
                color = Color.White,
                fontFamily = PoppinsFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUploadChildPhotoScreen() {
    ChildPhoto1(navController = rememberNavController())
}
