package com.tc.eat.presentation.screens.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tc.design.R
import com.tc.eat.domain.entities.Restaurant

@Composable
fun RestaurantDetailRow(modifier : Modifier = Modifier, restaurant: Restaurant){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconDetail(
            restaurant.rating.toString(),
            R.drawable.full_star_icon
        )
        IconDetail(
            restaurant.distanceTime.toString() +
                    stringResource(com.tc.eat.R.string.minute),
            R.drawable.clock_icon
        )
        IconDetail(
            restaurant.priceRange,
            R.drawable.sale_tag_icon
        )
    }
}