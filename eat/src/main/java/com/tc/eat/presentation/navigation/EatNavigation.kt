package com.tc.eat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.eat.presentation.screens.filters.FiltersScreen
import com.tc.eat.presentation.screens.home.HomeScreen
import com.tc.eat.presentation.screens.restaurant.RestaurantScreen
import com.tc.eat.presentation.screens.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
object Home
@Serializable
object Res
@Serializable
object Search
@Serializable
object Filter
@Serializable
object EatNavigation

@Preview
@Composable
fun EatNavigation(navToApp :() -> Unit){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ){
        composable<Home>{ HomeScreen(
            navToApp = {navToApp()},
            navToSearch = {navController.navigate(route = Search)},
            navToFilters = {navController.navigate(route = Filter)},
            navToRes = {navController.navigate(route = Res)}
        ) }
        composable<Res>{ RestaurantScreen(
            navToHome = {navController.navigate(route = Home)}
        ) }
        composable<Search>{ SearchScreen(
            navToHome ={navController.navigate(route = Home)},
            navToFilters = {navController.navigate(route = Filter)}
        ) }
        composable<Filter>{
            FiltersScreen(
                navBack = {navController.popBackStack()}
            )}
    }
}
