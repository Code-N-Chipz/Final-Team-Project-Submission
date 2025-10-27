package com.tc.doctor.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Resources:
// https://medium.com/@jpmtech/navigation-in-jetpack-compose-c9e1fcfd2cdd



// Declare a centralized and sealed class of possible routes
sealed class DoctorDest(val route: String) {
    data object Option : DoctorDest("option")
    data object Filters : DoctorDest("filters")
    data object Map : DoctorDest("map")
    data object Confirmation : DoctorDest("confirmation")
    data object Search : DoctorDest("search")
}

/** Simple extension to navigate using the strongly-typed destination. */
fun NavController.navigateTo(dest: DoctorDest) = navigate(dest.route)

/** Optional: pop up to start destination and then navigate. */
fun NavController.navigateSingleTopTo(dest: DoctorDest) {
    navigate(dest.route) {
        launchSingleTop = true
        restoreState = true
    }
}

/**
 * A NavHost you can drop into your screen. It registers all known routes.
 * You can pass lambdas for each destination to render your real screens.
 * If not provided, lightweight placeholders are shown so it compiles safely.
 */
@Composable
fun DoctorNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: DoctorDest = DoctorDest.Option,
    option: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Option", onNext = { nc.navigateTo(DoctorDest.Filters) })
    },
    filters: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Filters", onNext = { nc.navigateTo(DoctorDest.Map) })
    },
    doctorMap: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Doctor Map", onNext = { nc.navigateTo(DoctorDest.Search) })
    },
    confirmation: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Confirmation", onNext = { nc.navigateTo(DoctorDest.Option) })
    },
    search: @Composable (NavBackStackEntry, NavController) -> Unit = { _, nc ->
        PlaceholderScreen(title = "Search", onNext = { nc.navigateTo(DoctorDest.Confirmation) })
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(DoctorDest.Option.route) { backStackEntry -> option(backStackEntry, navController) }
        composable(DoctorDest.Filters.route) { backStackEntry -> filters(backStackEntry, navController) }
        composable(DoctorDest.Map.route) { backStackEntry -> doctorMap(backStackEntry, navController) }
        composable(DoctorDest.Confirmation.route) { backStackEntry -> confirmation(backStackEntry, navController) }
        composable(DoctorDest.Search.route) { backStackEntry -> search(backStackEntry, navController) }
    }
}


// default ui when supplied composable doesn't work
@Composable
private fun PlaceholderScreen(title: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "[$title]\nWire up your real composable by passing a lambda to DoctorNavHost().")
        Button(onClick = onNext) { Text("Next") }
    }
}


/*
@Composable
fun DoctorRoot() {
    DoctorNavHost(
        // Provide the real Search screen and hook its Home click to navigate
        search = { _, navController ->
            SearchDoctorScreen(
                onHomeClick = {
                    // Simple forward navigation:
                    // navController.navigateTo(DoctorDest.Option)

                    // Or singleTop + restore state, popping up to start:
                    navController.navigate(DoctorDest.Option.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
        // ...other routes
    )
}
 */