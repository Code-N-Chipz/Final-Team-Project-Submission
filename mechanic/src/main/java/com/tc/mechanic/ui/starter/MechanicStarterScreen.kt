package com.tc.mechanic.ui.starter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tc.mechanic.R


@Composable
fun MechanicStarterScreen(
    onLetsGo: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val isPreview = LocalInspectionMode.current

    Surface (modifier = modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Illustration placeholder
            Image(
                painter = painterResource(R.drawable.mechanic),
                contentDescription = "Mechanic Illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(text = "Mechanic", fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF0B3A66))
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis lobortis sit amet odio in egestas. Pellentesque ultricies justo.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.weight(1f))

            Button (
                onClick = onLetsGo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
            ) {
                Text(text = "Let’s go", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true, name = "Mechanic Intro Preview")
@Composable
fun MechanicIntroPreview() {
    MechanicStarterScreen(onLetsGo = {})
}