package com.tc.eat.presentation.screens.restaurant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.eat.domain.entities.MenuItem
import com.tc.eat.domain.util.MenuItemCategories
import com.tc.eat.presentation.screens.composables.PriceTagText
import theme.textTertiary

@Composable
fun FeaturedItems(modifier: Modifier = Modifier) {
    val featureMenu = listOf(
        MenuItem(
            name = "Spinach and ricotta raviolis",
            ingredients = listOf("Spinach", "Ricotta", "Pasta"),
            menuCategory = MenuItemCategories.ENTREE,
            price = 13.50f,
            isPopular = true,
            isAvailable = true,
            foodImage = com.tc.eat.R.drawable.spaghetti
        ),
        MenuItem(
            name = "Spinach and ricotta raviolis",
            ingredients = listOf("Spinach", "Ricotta", "Pasta"),
            menuCategory = MenuItemCategories.ENTREE,
            price = 13.50f,
            isPopular = true,
            isAvailable = true,
            foodImage = com.tc.eat.R.drawable.spaghetti
        )
    )
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            append(stringResource(com.tc.eat.R.string.feature))
            withStyle(SpanStyle(color = textTertiary)) {
                append("   9")
            }
        },
        style = MaterialTheme.typography.labelLarge,
        fontSize = 20.sp
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(192.dp)
            .padding(horizontal = 16.dp)
    ) {
        items(featureMenu) { feature ->
            Column {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .width(340.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = painterResource(feature.foodImage),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = feature.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                PriceTagText(
                    modifier = Modifier.padding(start = 4.dp),
                    price = feature.price
                )
            }
        }
    }

}