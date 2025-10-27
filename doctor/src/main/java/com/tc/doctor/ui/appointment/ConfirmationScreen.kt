package com.tc.doctor.ui.appointment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.tc.design.R as CoreDraw
import com.tc.doctor.R

@Composable
fun ConfirmationScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        TopBar()
        Image(
            painter = painterResource(R.drawable.img_confirmation),
            contentDescription = "Confirmation Image",
            modifier = Modifier.size(300.dp)
        )
        Text(text = "Confirmation",
            style = theme.typography.titleLarge.copy(fontSize = 40.sp))
        Text(text = "You can find your appointment in the agenda section of your application.",
            style = theme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Button(onClick = {}) {
            Icon(
                painter = painterResource(CoreDraw.drawable.home_icon),
                contentDescription = "Go Home Button"
            )
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                contentDescription = "Back Button",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun ConfirmationScreenPreview(){
    ConfirmationScreen()
}