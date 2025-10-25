package com.tc.auth.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import javax.inject.Inject

class ComposeAppNavigator @Inject constructor() : AppNavigator {
    private var navController: NavHostController? = null
    fun setController(controller: NavHostController) { navController = controller }
    override fun navigateTo(route: String) { navController?.navigate(route) }
    override fun goBack(){ navController?.popBackStack()}
}