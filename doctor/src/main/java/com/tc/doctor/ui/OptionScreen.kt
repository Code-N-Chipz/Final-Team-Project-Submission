package com.tc.doctor.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tc.design.R as CoreDraw
import com.tc.doctor.R
import com.tc.ui.CommonButton

@Composable
fun OptionScreen(navController: NavController? = null) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
            ) {
            IconButton(onClick = {
                navController?.popBackStack()
            } ) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                    contentDescription = "Back Button",
                    tint = Color.Unspecified
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.img_doctor_options),
            contentDescription = "Filler Image",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier
            .height(10.dp))
        Text(
            text = "Choose your option",
            style = theme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )
        Spacer(modifier = Modifier
            .height(10.dp))
        Text(
            text = "We're here to help",
            style = theme.typography.bodyLarge.copy(color = theme.textPenternary),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .height(30.dp))

        val greenButton = ButtonDefaults.buttonColors(containerColor = theme.secondaryColor)
        CommonButton("Make the Diagnosis", color = greenButton, onClick = {})

        CommonButton("Make an Appointment", onClick = {navController?.navigate(DoctorDest.Filters.route)})
        Spacer(modifier = Modifier.height(10.dp))
    }
}




@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun OptionScreenPreview() {
    OptionScreen()
}