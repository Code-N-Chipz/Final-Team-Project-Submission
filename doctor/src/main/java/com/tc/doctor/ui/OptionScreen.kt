package com.tc.doctor.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tc.design.R as CoreDraw
import com.tc.doctor.R

@Composable
fun OptionScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
            ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                    contentDescription = "Back Button"
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.img_doctor_options),
            contentDescription = "Filler Image",
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = "Choose Path"
        )
        Text(
            text = "We're here to help"
        )
        Button(onClick = {}) {
            Text("Make the Diagnosis")
        }
        Button(onClick = {}) {
            Text("Make an Appointment")
        }
    }
}




@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun OptionScreenPreview() {
    OptionScreen()
}