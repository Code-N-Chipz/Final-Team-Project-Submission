package com.tc.learn.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import javax.inject.Inject

class ComposeAppNavigator @Inject constructor() : AppNavigator {

    private var navController: NavHostController? = null

    // Single method to set controller
    override fun setController(controller: NavController) {
        navController = controller as? NavHostController
    }

    override fun navigateTo(route: String) {
        navController?.navigate(route)
    }

    override fun goBack() {
        navController?.popBackStack()
    }
}
