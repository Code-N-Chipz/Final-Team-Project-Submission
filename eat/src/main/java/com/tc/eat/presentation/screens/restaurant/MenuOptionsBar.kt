package com.tc.eat.presentation.screens.restaurant

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.eat.domain.util.MenuItemCategories
import theme.primaryColor
import theme.textTertiary

@Composable
fun MenuOptionsBar(){
    val menuCategories = listOf(
        MenuItemCategories.POPULAR,
        MenuItemCategories.ENTREE,
        MenuItemCategories.DISH,
        MenuItemCategories.DRINK,
        MenuItemCategories.DESSERT
    )
    val (selectedMenuCategory, onMenuCategorySelected) = remember { mutableStateOf(menuCategories[0]) }
    var colorText : Color
    LazyRow(
        modifier = Modifier
            .selectableGroup()
    ) {
        items(menuCategories) { menuCategory ->
            colorText = if (menuCategory == selectedMenuCategory) {
                primaryColor
            } else{
                textTertiary
            }
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .selectable(
                        selected = (menuCategory == selectedMenuCategory),
                        onClick = {
                            onMenuCategorySelected(menuCategory)
                        },
                        role = Role.RadioButton
                    ),
                text = stringResource(menuCategory.categoryString),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = colorText
            )
        }
    }
}