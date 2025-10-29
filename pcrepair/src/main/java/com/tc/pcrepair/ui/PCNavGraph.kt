package com.tc.pcrepair.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.pcrepair.navigation.PCRepairRoute
import com.tc.pcrepair.ui.calender.CalenderView
import com.tc.pcrepair.ui.calender.CalenderViewModel
import com.tc.pcrepair.ui.filter.FilterViewModel
import com.tc.pcrepair.ui.filter.FiltersScreen
import com.tc.pcrepair.ui.map.PCMapScreen
import com.tc.pcrepair.ui.map.PCMapScreenViewModel
import com.tc.pcrepair.ui.search.PCSearchScreen
import com.tc.pcrepair.ui.search.PCSearchViewModel
import com.tc.pcrepair.ui.selectpc.PcRepairFormScreen
import com.tc.pcrepair.ui.selectpc.PcRepairSelectorViewModel
import com.tc.pcrepair.ui.starter.PcRepairStarterScreen
import com.tc.pcrepair.ui.starter.PcRepairStarterViewModel
import com.tc.pcrepair.ui.summery.PCOrderSummaryScreen
import com.tc.pcrepair.ui.summery.PCOrderSummaryViewModel


@Composable
fun PCRepairNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = PCRepairRoute.Starter.route){
        composable(PCRepairRoute.Starter.route) {
            val viewModel = remember { PcRepairStarterViewModel() }
            PcRepairStarterScreen(
                viewModel = viewModel,
                onLetsGo = { navController.navigate(PCRepairRoute.SelectPC.route) }
            )
        }

        composable(PCRepairRoute.SelectPC.route) {
            val viewModel = remember { PcRepairSelectorViewModel() }
            PcRepairFormScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(PCRepairRoute.FilterPC.route) }
            )
        }
        composable(PCRepairRoute.FilterPC.route) {
            val viewModel = remember { FilterViewModel() }
            FiltersScreen (
                viewModel = viewModel,
                onApply = { navController.navigate(PCRepairRoute.MapPC.route) }
            )
        }

        composable(PCRepairRoute.MapPC.route) {
            val viewModel = remember { PCMapScreenViewModel() }
            PCMapScreen (
                viewModel = viewModel,
                onSelection = { navController.navigate(PCRepairRoute.SearchPC.route) }
            )
        }

        composable(PCRepairRoute.SearchPC.route) {
            val viewModel = remember { PCSearchViewModel() }
            PCSearchScreen (
                viewModel = viewModel,
                onSuccess = {},
                onCalenderClick = {navController.navigate(PCRepairRoute.CalenderPC.route) }
            )
        }

        composable(PCRepairRoute.CalenderPC.route) {
            val viewModel = remember { CalenderViewModel() }
            CalenderView (
                viewModel = viewModel,
                onConfirmed = { navController.navigate(PCRepairRoute.SummeryPC.route) }
            )
        }

        composable(PCRepairRoute.SummeryPC.route) {
            val viewModel = remember { PCOrderSummaryViewModel() }
            PCOrderSummaryScreen (
                viewModel = viewModel,
                onPlaceOrder = {  }
            )
        }
    }
}

@Composable
fun PCRepairEntryPoint() {
    val navController = rememberNavController()
    PCRepairNavGraph(navController = navController)
}