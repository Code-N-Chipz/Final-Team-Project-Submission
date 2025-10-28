package com.tc.houseclean

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.ui.R

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage().HomeScreen(navController) }
//        composable("childdetail") { YourChildScreen(navController) }
//        composable("childphoto") { ChildPhoto(navController) }
//        composable("childlist") { YourChildListScreen(navController) }
//        composable("selectbabysitter") { SelectBabysitterScreen(navController) }
//        composable("deposittime") { DepositTimeScreen(navController) }
//        composable("recoverytime") { RecoveryTimeScreen(navController) }
//        composable("choosedate") { ChooseDateEnhancedScreen(navController) }
//        composable("mapwithdetailscreen") { CaregiverMapScreen() }
//        composable("mapscreen") { MapScreen() }
//        composable("orderdetails") { OrderScreen() }
    }
}