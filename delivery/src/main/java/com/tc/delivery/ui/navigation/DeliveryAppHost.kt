package com.tc.delivery.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.delivery.ui.deliverystartingpage.DeliveryStartPage
import com.tc.delivery.ui.homepage.DeliveryHomePage

@Composable
fun DeliveryApp(
    parentNavController: NavController
){
    val navController = rememberNavController()

    NavHost(
        startDestination = "start",
        navController = navController
    ){
        composable("start"){
            DeliveryStartPage(
                navController = navController,
                onExitLaundry = {
                    parentNavController.popBackStack()
                }
            )
        }

        composable("home"){
            DeliveryHomePage(
                navController = navController,
                onExitLaundry = {
                    parentNavController.popBackStack()
                }
            )
        }
    }
}