package com.tc.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tc.auth.ui.navigation.ComposeAppNavigator
import com.tc.auth.ui.navigation.Routes
import com.tc.auth.ui.signin.SignInScreen
import com.tc.auth.ui.signup.confirmcode.ConfirmCodeScreen
import com.tc.auth.ui.signup.createcode.CreateCodeScreen
import com.tc.auth.ui.signup.onboarding.OnBoardingPageScreen
import com.tc.auth.ui.signup.signupscreen.SignupFormScreen
import com.tc.auth.ui.signup.verifyphone.VerifyPhoneScreen

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
        composable(Routes.SIGN_IN) {
            SignInScreen(
                navigator = navigator, // passes ComposeAppNavigator as AppNavigator
                name = "John"
            )
        }

        composable(Routes.SIGN_UP) {
            SignupFormScreen(
                onSignUpSuccess = { navigator.navigateTo(Routes.VERIFY_PHONE)  }, // need to verify this
            )
        }

        composable(Routes.VERIFY_PHONE) {
            VerifyPhoneScreen(
                onSuccess = { navigator.navigateTo(Routes.CREATE_CODE) })
        }

        composable(Routes.CREATE_CODE) {
            CreateCodeScreen(
                onSuccess = {navigator.navigateTo(Routes.CONFIRM_CODE)}
            )
        }
        composable(Routes.CONFIRM_CODE) {
            ConfirmCodeScreen(
                onSuccess = {navigator.navigateTo(Routes.ON_BOARDING)}
            )
        }
        composable(Routes.ON_BOARDING) {
            OnBoardingPageScreen(
                onSuccess = {navigator.navigateTo(Routes.SIGN_IN)}
            )
        }

    }

}