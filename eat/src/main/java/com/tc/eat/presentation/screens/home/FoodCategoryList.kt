package com.tc.eat.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.util.Categories
import com.tc.eat.domain.util.FoodIconCategories
import theme.textTertiary

@Composable
fun FoodCategoryList(modifier: Modifier = Modifier) {
    val foodIcons = listOf<FoodIconCategories>(
        FoodIconCategories(
            Categories.PIZZA,
            com.tc.eat.R.drawable.pizza
        ),
        FoodIconCategories(
            Categories.BURGER,
            com.tc.eat.R.drawable.burger
        ),
        FoodIconCategories(
            Categories.BREAKFAST,
            com.tc.eat.R.drawable.breakfast
        ),
        FoodIconCategories(
            Categories.ASIAN,
            com.tc.eat.R.drawable.asian
        ),
        FoodIconCategories(
            Categories.CUPCAKE,
            com.tc.eat.R.drawable.cupcake
        )
    )
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 8.dp)
    ) {
        items(foodIcons) { foodIcon ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FoodIconButton(foodIcon)
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(foodIcon.category.categoryString),
                    style = MaterialTheme.typography.bodyLarge,
                    color = textTertiary
                )
            }
        }
    }
}
