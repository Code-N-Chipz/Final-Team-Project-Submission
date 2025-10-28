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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.tc.design.R as CoreDraw
import com.tc.doctor.R
import com.tc.ui.CommonButton

@Composable
fun DoctorScreen(
    navController: NavController? = null,
    parentNavController: NavController? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { parentNavController?.popBackStack() }) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                    contentDescription = "Back Button",
                    tint = Color.Unspecified
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.img_welcome_doctor),
            contentDescription = "Welcome Image",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Health",
            style = theme.typography.titleLarge.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "CarePath finds nearby clinics, lets you book appointments quickly, and guides a brief symptom check to suggest next steps. See availability, get directions, and keep your data private unless you choose to share it.",
            style = theme.typography.bodyMedium,
            // TODO: need to fix color to be Pent
            color = theme.textPenternary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        CommonButton("Let's go", onClick = { navController?.navigate(DoctorDest.Option.route) })
        Spacer(modifier = Modifier.height(10.dp))
    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun DoctorScreenPreview() {
    DoctorScreen()
}