package com.tc.eat.presentation.screens.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.tc.design.R
import com.tc.eat.presentation.screens.composables.RestaurantCard
import theme.ICLICKIPAYTheme
import theme.primaryColor
import theme.primaryIconColor
import theme.textTertiary

@Composable
fun RestaurantScreen(navToHome: () -> Unit) {
    ICLICKIPAYTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { isTraversalGroup = true }
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                RestaurantBanner()
                FeaturedItems(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 88.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = textTertiary,
                )
                RestaurantMenuListings()
            }
            RestaurantCard(
                modifier = Modifier
                    .padding(top = 182.dp)
                    .width(356.dp)
                    .height(120.dp)
            )
        }
        IconButton(
            modifier = Modifier
                .padding(start = 8.dp, top = 20.dp)
                .size(52.dp)
                .background(shape = CircleShape, color = primaryIconColor),
            onClick = { navToHome() }
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(R.drawable.arrow_left_orange_icon),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun RestaurantBanner() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .background(
                color = primaryColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp)),
        painter = painterResource(com.tc.eat.R.drawable.spaghetti),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun RestaurantMenuListings() {
    MenuOptionsBar()
    MenuOptionsList()
}