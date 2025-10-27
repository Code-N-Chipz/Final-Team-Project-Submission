package com.tc.doctor.ui

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R as CoreDraw
import com.tc.doctor.R

@Composable
fun DoctorScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
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
            painter = painterResource(R.drawable.img_welcome_doctor),
            contentDescription = "Welcome Image",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Text(
            text = "Health",
            style = theme.typography.titleLarge.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Text(
            text = "CarePath finds nearby clinics, lets you book appointments quickly, and guides a brief symptom check to suggest next steps. See availability, get directions, and keep your data private unless you choose to share it.",
            style = theme.typography.bodyMedium,
            // TODO: need to fix color to be Pent
            color = theme.textQuaternary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(vertical = 40.dp))
        Button(
            onClick = {},
        ) {
            Text("Let's go")
        }
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun DoctorScreenPreview() {
    DoctorScreen()
}