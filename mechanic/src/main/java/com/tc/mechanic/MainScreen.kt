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
import com.tc.mechanic.ui.calender.CalenderViewModel
import com.tc.mechanic.ui.filter.FilterViewModel
import com.tc.mechanic.ui.filter.FiltersScreen
import com.tc.mechanic.ui.map.MapScreen
import com.tc.mechanic.ui.map.MapViewModel
import com.tc.mechanic.ui.search.MechanicSearchScreen
import com.tc.mechanic.ui.search.MechanicSearchViewModel
import com.tc.mechanic.ui.selectcar.MechanicCarSelectorViewModel
import com.tc.mechanic.ui.selectcar.MechanicFormScreenWithDropdowns
import com.tc.mechanic.ui.starter.MechanicStarterScreen
import com.tc.mechanic.ui.starter.MechanicStarterViewModel
import com.tc.mechanic.ui.summery.OrderSummaryScreen
import com.tc.mechanic.ui.summery.OrderSummaryViewModel


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
        startDestination = Routes.STARTER
    ){
        composable(Routes.STARTER) {
            val viewModel: MechanicStarterViewModel = hiltViewModel()
            MechanicStarterScreen (
                viewModel,
                onSuccess = { navigator.navigateTo(Routes.SELECT_CAR) }
            )
        }

        composable(Routes.SELECT_CAR) {
            val viewModel: MechanicCarSelectorViewModel = hiltViewModel()
            MechanicFormScreenWithDropdowns(
                viewModel,
                onSuccess = { navigator.navigateTo(Routes.FILTER)  }, // need to verify this
            )
        }

        composable(Routes.FILTER) {
            val viewModel: FilterViewModel = hiltViewModel()
            FiltersScreen (
                viewModel,
                onApply = { navigator.navigateTo(Routes.MAP) })
        }

        composable(Routes.MAP) {
            val viewModel: MapViewModel = hiltViewModel()
            MapScreen (
                viewModel,
                onSuccess = {navigator.navigateTo(Routes.SEARCH)}
            )
        }
        composable(Routes.SEARCH) {
            val viewModel: MechanicSearchViewModel = hiltViewModel()
            MechanicSearchScreen(
                viewModel,
                onCalenderClick = {navigator.navigateTo(Routes.CALENDER)}
            )
        }
        composable(Routes.CALENDER) {
            val viewModel: CalenderViewModel = hiltViewModel()
            CalenderView (
                viewModel,
                onConfirmed = { navigator.navigateTo(Routes.SUMMERY) }
            )
        }
        composable(Routes.SUMMERY) {
            val viewModel: OrderSummaryViewModel = hiltViewModel()
            OrderSummaryScreen (
                viewModel
//                onSuccess = {navigator.navigateTo(Routes.SIGN_IN)}
            )
        }

    }

}