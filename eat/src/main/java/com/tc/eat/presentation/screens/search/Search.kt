package com.tc.eat.presentation.screens.search

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R
import com.tc.eat.domain.Restaurant
import theme.ICLICKIPAYTheme
import theme.primaryColor
import theme.textPrimary
import theme.textTertiary


@Composable
fun SearchScreen() {
    ICLICKIPAYTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .semantics { isTraversalGroup = true }) {
            Scaffold(topBar = { SearchTopBar() }) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    RestaurantResults()
                }
            }
            SearchBar()
        }
    }
}

@Composable
private fun SearchTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .background(primaryColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.arrow_left_white_icon),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
        Text(
            stringResource(com.tc.eat.R.string.search),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 26.sp,
            color = textPrimary
        )
        IconButton(onClick = {}) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.options_sliders_orange_icon),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

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
fun RestaurantList() {
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
fun RestaurantItem() {
    val restaurant = Restaurant(
        name = "Little India",
        subtitle = "Indian food",
        rating = 4.8f,
        distanceTime = 15,
        priceRange = "$$$",
        logoImage = com.tc.eat.R.drawable.indian_rest_logo
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 26.dp)
            .fillMaxWidth()
            .height(120.dp)
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
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 1.dp,
            color = textTertiary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
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
}

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

@Composable
fun SearchBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(start = 16.dp, end = 16.dp, top = 84.dp)
            .border(width = 1.dp, color = textTertiary, shape = RoundedCornerShape(12.dp))
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(14.dp),
                spotColor = Color.Black.copy(alpha = 5.5f),
                ambientColor = Color.Black.copy(alpha = 5.7f)
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        TextField(
            value = "",
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTrailingIconColor = textTertiary,
                unfocusedTrailingIconColor = textTertiary,
            ),
            placeholder = { Text(text = "Search", color = textTertiary) }
        )
    }
}
