package com.tc.eat.presentation.screens.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import theme.ICLICKIPAYTheme
import theme.primaryColor
import theme.textQuaternary

@Composable
fun FiltersScreen() {
    ICLICKIPAYTheme {
        Scaffold(topBar = { FilterTopBar() }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SortByDropDownMenu()
                PriceRangeSlider()
                CategoryCheckboxes()
            }
        }
    }
}

@Composable
private fun FilterTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}) {
            Icon(
                modifier = Modifier.size(30.dp),
                painter = painterResource(R.drawable.arrow_left_orange_icon),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
        Text(
            stringResource(com.tc.eat.R.string.filters),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 26.sp,
            color = textQuaternary
        )
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = stringResource(com.tc.eat.R.string.clear),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 16.sp,
            color = primaryColor
        )
    }
}