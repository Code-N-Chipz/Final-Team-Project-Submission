package com.tc.learn.utils.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.learn.ui.screen.appointment.AppointmentScreen
import com.tc.learn.ui.screen.calander.CalendarScreen
import com.tc.learn.ui.screen.map.MapScreen
import com.tc.learn.ui.screen.order.OrderScreen
import com.tc.learn.ui.screen.search.SearchScreen
import com.tc.learn.ui.screen.start.StartScreen
import com.tc.learn.ui.screen.teacher.TeacherScreen
import com.tc.learn.utils.navigation.LearnNavHost

/**
 * Entry point for the Learn (Teacher Finder) module.
 * Nested NavHost so it can run inside your super app's navigation.
 */
@Composable
fun LearnNavHost(
    navigator: AppNavigator,
    startDestination: String = "learn_start"
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        if (navigator is LearnNavHost) {
            navigator.setController(navController)
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable("learn_start") {
            StartScreen(navigator)
        }

        composable("search") {
            SearchScreen(navigator)
        }

        composable("teacher_detail/{teacherId}") {
            val teacherId = it.arguments?.getString("teacherId")
            TeacherScreen(navigator, teacherId)
        }

        composable("appointment/{teacherId}") {
            val teacherId = it.arguments?.getString("teacherId")
            AppointmentScreen(navigator, teacherId)
        }

        composable("calendar") {
            CalendarScreen(navigator)
        }

        composable("map") {
            MapScreen(navigator)
        }

        composable("order/{orderId}") {
            val orderId = it.arguments?.getString("orderId")
            OrderScreen(navigator, orderId)
        }
    }
}

