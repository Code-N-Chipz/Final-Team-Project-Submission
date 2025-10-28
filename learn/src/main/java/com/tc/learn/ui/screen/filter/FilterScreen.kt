package com.tc.learn.ui.screen.filter

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tc.learn.ui.navigation.AppNavigator
import androidx.compose.material3.Slider
import androidx.hilt.navigation.compose.hiltViewModel
import com.tc.learn.ui.viewmodel.TeacherViewModel

@Composable
fun FilterScreen(
    navigator: AppNavigator,
    viewModel: TeacherViewModel = hiltViewModel()
) {
    var sortOption by remember { mutableStateOf("Recommended") }
    val sortOptions = listOf("Recommended", "Price: Low to High", "Price: High to Low")

    var priceRange by remember { mutableStateOf(50f) } // max price
    var starRange by remember { mutableStateOf(5f) } // max star rating



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Filter Options", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        // --- Sort Dropdown ---
        Text("Sort by")
        DropdownMenuDemo(sortOptions, sortOption) { selected ->
            sortOption = selected
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Price Range ---
        Text("Max Price: \$${priceRange.toInt()}")
        Slider(
            value = priceRange,
            onValueChange = { priceRange = it },
            valueRange = 0f..500f
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Star Range ---
        Text("Minimum Star Rating: ${starRange.toInt()}")
        Slider(
            value = starRange,
            onValueChange = { starRange = it },
            valueRange = 0f..5f,
            steps = 4
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            // Apply filters and navigate if needed
            // Example: appNavigator.navigateTo(NavRoute.Search.route)

            //Call filter functions from repo by viewmodel

            //need to make viewmodel available, and add new function to repository
            viewModel.applyFilterPageFilters(sortOption, priceRange, starRange)

            navigator.navigateTo("search") // close filter screen

        }) {
            Text("Apply Filters")
        }
    }
}

@Composable
fun DropdownMenuDemo(options: List<String>, selectedOption: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedOption)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
