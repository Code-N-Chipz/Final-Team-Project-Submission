package com.tc.learn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.tc.learn.ui.screen.map.MapScreen
import com.tc.learn.ui.screen.start.StartScreen
import com.tc.learn.ui.screen.search.SearchScreen
import com.tc.learn.ui.screen.teacher.TeacherScreen
import com.tc.learn.ui.viewmodel.TeacherViewModel
import com.tc.learn.utils.navigation.NavRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    navigator: AppNavigator,
) {
    // Set the NavController in our navigator
    navigator.setController(navController)
    val viewModel: TeacherViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Start.route,
    ) {

        // --- Start Screen ---
        composable(NavRoute.Start.route) {
            StartScreen(
                name = "John Doe",
                navigator = navigator,
                onButtonClick = {
                    navigator.navigateTo("search")
                },

                //Apply Theme Colours
//                colors = primaryColor,
            )
        }

        // --- Search Screen ---
        composable(NavRoute.Search.route) {
            SearchScreen(
                viewModel = viewModel,
                onTeacherClick = { teacher ->
                    //On clicking the button, Navigate to detail detail, passing the teacher.id
                    navigator.navigateTo(NavRoute.TeacherDetail.passId(teacher.id))
                },
                onMapClick = { teacher ->
                    navigator.navigateTo(NavRoute.Map.passId(teacher.id))
                },
                onCalendarClick = { teacher ->
//                    navigator.navigateTo(NavRoute.Calander.passId(teacher.id))
                    navigator.navigateTo(NavRoute.Calander.route)
                }
            )
        }

        // --- Teacher Detail Screen ---
        composable(NavRoute.TeacherDetail.route) { backStackEntry ->
            val teacherId: String =
                backStackEntry.arguments?.getString("teacherId") ?: return@composable
            TeacherScreen(
                navigator = navigator,
                teacherId = teacherId,
                onButtonClick = {
                    navigator.navigateTo("calendar")
                },
            )
        }

        // --- Map Screen ---
        composable(NavRoute.Map.route) { backStackEntry ->
            val teacherId = backStackEntry.arguments?.getString("teacherId") ?: return@composable
            MapScreen(
                navigator = navigator,
                teacherId = teacherId,
                viewModel = TODO(),
            )
        }

    }
}
