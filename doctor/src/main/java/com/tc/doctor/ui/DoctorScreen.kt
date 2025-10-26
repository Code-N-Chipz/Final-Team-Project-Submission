package com.tc.doctor.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.tc.design.R as CoreDraw

@Composable
fun DoctorScreen() {
    Column {
        Row {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(CoreDraw.drawable.arrow_left_orange_icon),
                    contentDescription = "Back Button"
                )
            }
        }
    }

}
