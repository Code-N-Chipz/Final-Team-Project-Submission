package com.tc.doctor.ui.appointment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tc.design.R as CoreDraw

@Composable
fun SearchDoctorScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        TopBar()
    }
}

@Composable
private fun TopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.home_icon),
                    contentDescription = "Home Icon"
                )
            }
        }
        Row {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.options_gray_icon),
                    contentDescription = "Home Icon"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.pin_gray_icon),
                    contentDescription = "Home Icon"
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFFL)
@Composable
private fun SearchDoctorScreenPreview() {
    SearchDoctorScreen()
}