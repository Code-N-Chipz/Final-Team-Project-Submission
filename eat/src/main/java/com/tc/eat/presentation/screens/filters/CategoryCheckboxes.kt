package com.tc.eat.presentation.screens.filters

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tc.eat.domain.util.Categories
import theme.primaryColor
import theme.textTertiary
@Composable
fun CategoryCheckboxes() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        CategoryTitle()
        CheckboxFamily()
    }
}
@Composable
private fun CategoryTitle() {
    Row(
        modifier = Modifier.padding(vertical = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(com.tc.eat.R.string.categories),
            color = textTertiary
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            thickness = 1.dp,
            color = textTertiary
        )
    }
}

@Composable
private fun CheckboxFamily() {
    val categories = listOf(
        Categories.AFRICAN,
        Categories.AMERICAN,
        Categories.ITALIAN,
        Categories.MEXICAN,
        Categories.FRENCH,
        Categories.INDIAN,
        Categories.TRADITIONAL,
        Categories.BREAKFAST,
        Categories.SPICY,
        Categories.BURGER,
        Categories.FRIES
    )
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ){
        items(categories){ category ->
            CategoryItem(category.categoryString)
        }
    }
}

@Composable
private fun CategoryItem(@StringRes name : Int) {
    var checked by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            stringResource(name),
            color = textTertiary
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(
                checkedColor = primaryColor,
                uncheckedColor = textTertiary
            )
        )
    }
}