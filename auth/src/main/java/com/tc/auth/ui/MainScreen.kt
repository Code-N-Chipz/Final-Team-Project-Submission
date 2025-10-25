package com.tc.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.auth.ui.navigation.ComposeAppNavigator
import com.tc.auth.ui.signin.SignInScreen
import com.tc.auth.ui.signup.signupscreen.SignupFormScreen

@Composable
fun MainScreen(
    navigator: ComposeAppNavigator = hiltViewModel()
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        navigator.setController(navController)
    }

    NavHost(navController = navController, startDestination = "signin") {
        composable("signin") {
            SignInScreen(
                navigator = navigator, // passes ComposeAppNavigator as AppNavigator
                name = "John"
            )
        }
        composable("signup") { SignupFormScreen(onSignUpSuccess = {}) }
    }
}