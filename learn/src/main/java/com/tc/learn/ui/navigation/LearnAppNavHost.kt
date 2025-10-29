package com.tc.learn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tc.learn.ui.screen.map.MapScreen
import com.tc.learn.ui.screen.start.StartScreen
import com.tc.learn.ui.screen.search.SearchScreen
import com.tc.learn.ui.screen.teacher.TeacherScreen
import com.tc.learn.ui.viewmodel.TeacherViewModel
import com.tc.learn.utils.navigation.NavRoute

@Composable
fun LearnAppNavHost(parentNavController: NavController) {
    val navController = rememberNavController()            // internal navController
    val navigator = remember { ComposeAppNavigator() }     // navigator instance
    navigator.setControllers(internal = navController, parent = parentNavController)

    NavHost(
        navController = navController,
        startDestination = NavRoute.Start.route
    ) {

        // --- Start Screen ---
        composable(NavRoute.Start.route) {
            val viewModel: TeacherViewModel = hiltViewModel()
            StartScreen(
                name = "John Doe",
                navigator = navigator,
                onButtonClick = { navigator.navigateTo(NavRoute.Search.route) }
            )
        }
        // --- Search Screen ---
        composable(NavRoute.Search.route) {
            val viewModel: TeacherViewModel = hiltViewModel()   // activity-scoped
            SearchScreen(
                viewModel = viewModel,
                navigator = navigator,
                onTeacherClick = { teacher ->
                    navigator.navigateTo(NavRoute.TeacherDetail.passId(teacher.id))
                },
                onMapClick = { teacher ->
                    navigator.navigateTo(NavRoute.Map.passId(teacher.id))
                },
                onCalendarClick = { teacher ->
                    navigator.navigateTo(NavRoute.Calendar.passId(teacher.id))
                }
            )
        }
        // --- Teacher Detail Screen ---
        composable(
            route = NavRoute.TeacherDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val teacherId = it.arguments?.getString("id") ?: return@composable
            TeacherScreen(
                navigator = navigator,
                teacherId = teacherId,
                onButtonClick = {
                    navigator.navigateTo(NavRoute.Calendar.route)
                }
            )
        }

        // --- Map Screen ---
        composable(
            route = NavRoute.Map.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val teacherId = it.arguments?.getString("id") ?: return@composable
            MapScreen(
                navigator = navigator,
                teacherId = teacherId
            )
        }
    }
}
