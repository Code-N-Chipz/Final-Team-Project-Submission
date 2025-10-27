package com.tc.eat.presentation.screens.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun IconDetail(restaurantDetail: String = "", @DrawableRes iconId: Int) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(iconId),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Text(
            text = restaurantDetail,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}