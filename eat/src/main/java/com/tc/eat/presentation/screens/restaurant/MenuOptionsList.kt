package com.tc.eat.presentation.screens.restaurant

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.entities.MenuItem
import com.tc.eat.domain.util.MenuItemCategories

@Composable
fun MenuOptionsList() {
    val menuByCategory = listOf(
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
            name = "Mama pastas",
            ingredients = listOf("Tomato", "Ricotta", "Parmesan", "Pasta"),
            menuCategory = MenuItemCategories.ENTREE,
            price = 14.50f,
            isPopular = true,
            isAvailable = false,
            foodImage = com.tc.eat.R.drawable.spaghetti
        )
    )
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(menuByCategory) { menuItem ->
            MenuItemRow(menuItem)
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                thickness = 1.dp
            )
        }
    }
}