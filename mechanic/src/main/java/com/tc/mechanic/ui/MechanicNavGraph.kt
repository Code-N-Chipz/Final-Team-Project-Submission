package com.tc.mechanic.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.mechanic.navigation.navigation.MechanicRoute
import com.tc.mechanic.ui.filter.FilterViewModel
import com.tc.mechanic.ui.filter.FiltersScreen
import com.tc.mechanic.ui.selectcar.MechanicCarSelectorViewModel
import com.tc.mechanic.ui.selectcar.MechanicFormScreenWithDropdowns
import com.tc.mechanic.ui.starter.MechanicStarterScreen
import com.tc.mechanic.ui.starter.MechanicStarterViewModel

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
                onApply = {}
            )
        }
    }
}

@Composable
fun MechanicEntryPoint() {
    val navController = rememberNavController()
    MechanicNavGraph(navController = navController)
}