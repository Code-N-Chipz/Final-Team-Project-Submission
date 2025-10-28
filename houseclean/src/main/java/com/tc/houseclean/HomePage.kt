package com.tc.houseclean

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tc.ui.R

class HomePage {
    val SarabunFontFamily = FontFamily(
        Font(R.font.sarabun_regular, weight = FontWeight.Normal),
        Font(R.font.sarabun_medium, weight = FontWeight.Medium),
        Font(R.font.sarabun_semibold, weight = FontWeight.SemiBold)
    )

    @Composable
    fun HomeScreen(navController: NavHostController) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(42.dp)
                    .offset(6.dp, 24.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = colorResource(id = R.color.brand_orange)
                )
            }

            Image(
                painter = painterResource(R.drawable.ic_home),
                contentDescription = "Tinted Image",
                modifier = Modifier
                    .size(293.dp, 264.dp)
                    .offset(40.dp, 77.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                // "Babysitter" text
                Text(
                    text = "House Cleaning",
                    fontFamily = SarabunFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    color = colorResource(R.color.black)
                )

                Spacer(modifier = Modifier.height(16.dp)) // space below title

                // Paragraph text
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                            "Duis lobortis sit amet odio in egestas. Pellen tesque ultricies justo.",
                    fontFamily = SarabunFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFFA6AAB4),
                    modifier = Modifier.padding(horizontal = 20.dp),
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp)) // space before button

            }

            Button(
                onClick = { navController.navigate("childdetail") },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.brand_orange)),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .width(257.dp)
                    .height(56.dp)
                    .align(Alignment.Center) // horizontal center
                    .offset(y = 250.dp) // push it down relative to center
            ) {
                Text(
                    text = "Let's go",
                    fontFamily = SarabunFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun HomeScreenPreview() {
        HomeScreen(navController = rememberNavController())
    }
}