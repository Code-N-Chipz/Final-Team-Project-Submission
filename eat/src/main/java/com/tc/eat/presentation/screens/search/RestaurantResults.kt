package com.tc.eat.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.entities.Restaurant
import com.tc.eat.presentation.screens.composables.RestaurantDetailRow
import com.tc.eat.presentation.screens.composables.RestaurantHeaderInfo
import theme.textTertiary

@Composable
fun RestaurantResults() {
    Text(
        text = buildAnnotatedString {
            append(stringResource(com.tc.eat.R.string.restaurants))
            withStyle(SpanStyle(color = textTertiary)) {
                append("   3")
            }
        },
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(start = 26.dp, bottom = 32.dp),
    )
    RestaurantList()
}

@Composable
private fun RestaurantList() {
    val restaurantList = listOf(1, 2, 3)
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(restaurantList) { restaurant ->
            RestaurantItem()
            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                thickness = 1.dp,
                color = textTertiary
            )
        }
    }
}

@Composable
private fun RestaurantItem() {
    val restaurant = Restaurant(
        name = "Little India",
        subtitle = "Indian food",
        rating = 4.8f,
        distanceTime = 15,
        priceRange = "$$$",
        logoImage = com.tc.eat.R.drawable.indian_rest_logo
    )
    RestaurantHeaderInfo(
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .fillMaxWidth()
            .height(60.dp),
        restaurant = restaurant
    )
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 26.dp),
        thickness = 1.dp,
        color = textTertiary
    )
    RestaurantDetailRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 26.dp),
        restaurant = restaurant
    )
}
