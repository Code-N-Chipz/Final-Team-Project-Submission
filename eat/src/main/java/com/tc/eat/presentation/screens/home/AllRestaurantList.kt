package com.tc.eat.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R
import com.tc.eat.domain.entities.Restaurant
import com.tc.eat.presentation.screens.composables.RestaurantCard
import theme.textQuaternary
import theme.textTertiary

@Composable
fun AllRestaurantsList(modifier: Modifier = Modifier, navToFilters : () -> Unit) {
    val allRes = listOf(
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
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(com.tc.eat.R.string.all_res),
                style = MaterialTheme.typography.headlineMedium,
                color = textQuaternary,
                fontSize = 24.sp
            )
            IconButton(onClick = {navToFilters()}) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.option_slider_orange_icon),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
        Text(
            text = stringResource(com.tc.eat.R.string.all_res),
            style = MaterialTheme.typography.headlineMedium,
            color = textTertiary,
            fontSize = 16.sp
        )
        LazyColumn(modifier = Modifier.height(500.dp)){
           restaurantCardSublist(allRes)
        }
    }
}

private fun LazyListScope.restaurantCardSublist(allRes : List<Restaurant>){
    items(allRes) { res ->
        RestaurantCard(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(110.dp)
        )
    }
}