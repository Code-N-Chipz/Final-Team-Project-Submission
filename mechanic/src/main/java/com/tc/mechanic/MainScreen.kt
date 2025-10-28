package com.tc.mechanic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.auth.ui.navigation.ComposeAppNavigator
import com.tc.auth.ui.navigation.Routes
import com.tc.mechanic.ui.calender.CalenderView
import com.tc.mechanic.ui.filter.FiltersScreen
import com.tc.mechanic.ui.map.MapScreen
import com.tc.mechanic.ui.search.MechanicSearchScreen
import com.tc.mechanic.ui.selectcar.MechanicFormScreenWithDropdowns
import com.tc.mechanic.ui.starter.MechanicStarterScreen
import com.tc.mechanic.ui.summery.OrderSummaryScreen


@Composable
fun MainScreen(
//    navigator: ComposeAppNavigator = hiltViewModel()
) {
    val navController = rememberNavController()

    val navigator: ComposeAppNavigator = remember { ComposeAppNavigator() }

    // Connect the controller with navigator instance
    LaunchedEffect(Unit) {
        navigator.setController(navController)
    }

    NavHost(
        navController = navController,
        startDestination = "signin"
    ){
        composable(Routes.STARTER) {
            MechanicStarterScreen (
                onSuccess = { navigator.navigateTo(Routes.SELECT_CAR) }
            )
        }

        composable(Routes.SELECT_CAR) {
            MechanicFormScreenWithDropdowns(
                onSuccess = { navigator.navigateTo(Routes.FILTER)  }, // need to verify this
            )
        }

        composable(Routes.FILTER) {
            FiltersScreen (
                onApply = { navigator.navigateTo(Routes.MAP) })
        }

        composable(Routes.MAP) {
            MapScreen (
                onSuccess = {navigator.navigateTo(Routes.SEARCH)}
            )
        }
        composable(Routes.SEARCH) {
            MechanicSearchScreen(
                onCalenderClick = {navigator.navigateTo(Routes.CALENDER)}
            )
        }
        composable(Routes.CALENDER) {
            CalenderView (
                onConfirmed = { navigator.navigateTo(Routes.SUMMERY) }
            )
        }
        composable(Routes.SUMMERY) {
            OrderSummaryScreen (
//                onSuccess = {navigator.navigateTo(Routes.SIGN_IN)}
            )
        }

    }

}