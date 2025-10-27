package com.tc.learn.ui.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun navigateTo(route: String)
    fun goBack()
    fun setController(controller: NavController)
}

