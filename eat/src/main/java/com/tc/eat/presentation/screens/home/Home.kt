package com.tc.eat.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.tc.design.R
import com.tc.eat.presentation.screens.composables.VectorPathContainer
import theme.ICLICKIPAYTheme
import theme.primaryIconColor

@Composable
fun HomeScreen(
    navToApp : () -> Unit,
    navToSearch: () -> Unit,
    navToFilters: () -> Unit,
    navToRes: () -> Unit
) {
    ICLICKIPAYTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { isTraversalGroup = true },
            contentAlignment = Alignment.TopCenter
        ) {
            Scaffold(
                topBar = { HomeTopBar(navToApp, navToSearch) }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    VectorPathContainer()
                    FoodCategoryList(modifier = Modifier.padding(top = 120.dp))
                    AllRestaurantsList(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 20.dp),
                        navToFilters
                    )
                }
                DeliveryAddressRow(modifier = Modifier.padding(top = 100.dp, start = 20.dp))
                FeaturedRestaurantList(modifier = Modifier.padding(top = 188.dp), navToRes)
            }
        }
    }
}

@Composable
private fun HomeTopBar(navToApp : () -> Unit, navToSearch: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .padding(start = 8.dp, top = 20.dp),
            onClick = {navToApp()}
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(R.drawable.home_icon),
                contentDescription = "",
                tint = primaryIconColor
            )
        }
        IconButton(
            modifier = Modifier
                .padding(end = 8.dp, top = 20.dp),
            onClick = { navToSearch() }
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(R.drawable.magnifying_glass_grey_two_icon),
                contentDescription = "",
                tint = primaryIconColor
            )
        }
    }
}
