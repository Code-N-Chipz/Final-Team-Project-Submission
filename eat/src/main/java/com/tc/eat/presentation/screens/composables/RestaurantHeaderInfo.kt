package com.tc.eat.presentation.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.entities.Restaurant
import theme.textTertiary

@Composable
fun RestaurantHeaderInfo(modifier : Modifier = Modifier, restaurant : Restaurant) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.height(52.dp)) {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = restaurant.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textTertiary
                )
            }
            Image(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(52.dp),
                painter = painterResource(restaurant.logoImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
    }
}