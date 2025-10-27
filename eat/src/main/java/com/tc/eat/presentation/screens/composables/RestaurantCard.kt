package com.tc.eat.presentation.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.entities.Restaurant
import theme.primaryIconColor
import theme.textTertiary

@Composable
fun RestaurantCard(modifier: Modifier = Modifier) {
    val restaurant = Restaurant(
        name = "Little India",
        subtitle = "Indian food",
        rating = 4.8f,
        distanceTime = 15,
        priceRange = "$$$",
        logoImage = com.tc.eat.R.drawable.indian_rest_logo
    )
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = primaryIconColor,
        shadowElevation = 8.dp
    ) {
        Column{
            RestaurantHeaderInfo(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp)
                    .height(52.dp),
                restaurant = restaurant
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal =  12.dp),
                thickness = 1.dp,
                color = textTertiary
            )
            RestaurantDetailRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)
                    .padding(horizontal = 12.dp),
                restaurant = restaurant
            )
        }
    }
}