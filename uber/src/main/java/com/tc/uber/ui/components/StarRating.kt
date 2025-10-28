package com.tc.uber.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import theme.primaryColor
import com.tc.design.R as D

@Composable
fun StarRating(modifier: Modifier = Modifier, rating : Int, onRatingChanged : (Int) -> Unit){
    var starRating by rememberSaveable {
        mutableStateOf(rating)
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        IconButton(onClick = {
            starRating = 1
        }) {
            Icon(
                painter = painterResource(D.drawable.full_star_icon),
                tint = if(starRating > 0) primaryColor else Color.LightGray,
                contentDescription = "driver rating"
            )
        }
        IconButton(onClick = {
            starRating = 2
        }) {
            Icon(
                painter = painterResource(D.drawable.full_star_icon),
                tint = if(starRating >1) primaryColor else Color.LightGray,
                contentDescription = "driver rating"
            )
        }

        IconButton(onClick = {
            starRating = 3
        }) {
            Icon(
                painter = painterResource(D.drawable.full_star_icon),
                tint = if(starRating >2) primaryColor else Color.LightGray,
                contentDescription = "driver rating"
            )
        }

        IconButton(onClick = {
            starRating = 4
        }) {
            Icon(
                painter = painterResource(D.drawable.full_star_icon),
                tint = if(starRating >3) primaryColor else Color.LightGray,
                contentDescription = "driver rating"
            )
        }

        IconButton(onClick = {
            starRating = 5
        }) {
            Icon(
                painter = painterResource(D.drawable.full_star_icon),
                tint = if(starRating >4) primaryColor else Color.LightGray,
                contentDescription = "driver rating"
            )
        }
    }
}