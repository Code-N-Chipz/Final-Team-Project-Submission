package com.tc.laundry.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.laundry.ui.filterspage.FiltersPage
import com.tc.laundry.ui.homepage.LaundryHomePage
import com.tc.laundry.ui.startpage.LaundryStartPage
import com.tc.laundry.ui.yourlaundrypage.YourLaundryPage

@Composable
fun LaundryApp(
    parentNavController: NavController
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start"
    ){
        composable("start") {
            LaundryStartPage(
                navController = navController,
                onExitLaundry = {
                    parentNavController.popBackStack()
                }
            )
        }
        composable("home") {
            LaundryHomePage(
                onExitLaundry = {
                    parentNavController.popBackStack()
                },
                navController = navController
            )
        }
        composable("your_laundry"){
            YourLaundryPage(
                navController = navController
            )
        }

        composable("filters"){
            FiltersPage(
                navController = navController
            )
        }
    }
}