package com.tc.eat.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.entities.Restaurant
import com.tc.eat.presentation.screens.composables.RestaurantCard

@Composable
fun FeaturedRestaurantList(modifier: Modifier = Modifier, navToRes: () -> Unit) {
    val featuredRestaurants = listOf(
        Restaurant(
            name = "Little India",
            subtitle = "Indian food",
            rating = 4.8f,
            distanceTime = 15,
            priceRange = "$$$",
            logoImage = com.tc.eat.R.drawable.spaghetti
        ),
        Restaurant(
            name = "Little India",
            subtitle = "Indian food",
            rating = 4.8f,
            distanceTime = 15,
            priceRange = "$$$",
            logoImage = com.tc.eat.R.drawable.spaghetti
        ),
        Restaurant(
            name = "Little India",
            subtitle = "Indian food",
            rating = 4.8f,
            distanceTime = 15,
            priceRange = "$$$",
            logoImage = com.tc.eat.R.drawable.spaghetti
        )
    )
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        items(featuredRestaurants) { featureRes ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .width(325.dp)
                    .height(300.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = { navToRes() }),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(featureRes.logoImage),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                RestaurantCard(
                    modifier = Modifier
                        .padding(top = 160.dp)
                        .width(300.dp)
                        .height(110.dp)
                )
            }
        }
    }
}