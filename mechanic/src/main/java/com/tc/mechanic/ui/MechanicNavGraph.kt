package com.tc.mechanic.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.mechanic.navigation.navigation.MechanicRoute
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
fun MechanicNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = MechanicRoute.Starter.route){
        composable (MechanicRoute.Starter.route) {
            val viewModel = remember { MechanicStarterViewModel() }
            MechanicStarterScreen(
                viewModel = viewModel,
                onSuccess = { navController.navigate(MechanicRoute.SelectCar.route) }
            )
        }

        composable (MechanicRoute.SelectCar.route) {
            val viewModel = remember { MechanicCarSelectorViewModel() }
            MechanicFormScreenWithDropdowns(
                viewModel = viewModel,
                onSuccess = { navController.navigate(MechanicRoute.Filter.route) }
            )
        }
        composable (MechanicRoute.Filter.route) {
            val viewModel = remember { FilterViewModel() }
            FiltersScreen (
                viewModel = viewModel,
                onApply = { navController.navigate(MechanicRoute.Map.route) }
            )
        }

        composable (MechanicRoute.Map.route) {
            val viewModel = remember { MapViewModel() }
            MapScreen (
                viewModel = viewModel,
                onSuccess = { navController.navigate(MechanicRoute.Search.route) }
            )
        }

        composable (MechanicRoute.Search.route) {
            val viewModel = remember { MechanicSearchViewModel() }
            MechanicSearchScreen (
                viewModel = viewModel,
                onSuccess = {},
                onCalenderClick = {navController.navigate(MechanicRoute.Calender.route) }
            )
        }

        composable (MechanicRoute.Calender.route) {
            val viewModel = remember { CalenderViewModel() }
            CalenderView (
                viewModel = viewModel,
                onConfirmed = { navController.navigate(MechanicRoute.Summery.route) }
            )
        }

        composable (MechanicRoute.Summery.route) {
            val viewModel = remember { OrderSummaryViewModel() }
            OrderSummaryScreen (
                viewModel = viewModel,
                onPlaceOrder = {  }
            )
        }
    }
}

@Composable
fun MechanicEntryPoint() {
    val navController = rememberNavController()
    MechanicNavGraph(navController = navController)
}