package com.tc.learn.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import javax.inject.Inject

class ComposeAppNavigator : AppNavigator {
    private var internalController: NavController? = null
    private var parentController: NavController? = null

    fun setControllers(internal: NavController, parent: NavController? = null) {
        internalController = internal
        parentController = parent
    }

    override fun navigateTo(route: String) {
        internalController?.let {
            // Only use internal for navigation within the module
            it.navigate(route)
        } ?: parentController?.navigate(route)
    }

    override fun goBack() {
        internalController?.let {
            if (!it.popBackStack()) {
                parentController?.popBackStack()
            }
        } ?: parentController?.popBackStack()
    }

    override fun setController(controller: NavController) {
        internalController = controller
    }
}
