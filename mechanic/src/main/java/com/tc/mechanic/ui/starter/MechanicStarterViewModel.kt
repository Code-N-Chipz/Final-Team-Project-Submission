package com.tc.mechanic.ui.starter

import androidx.lifecycle.ViewModel
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import javax.inject.Inject

class MechanicStarterViewModel @Inject constructor(
    private val navigator: AppNavigator
): ViewModel() {
    fun submit(onSuccess: ()-> Unit, onError: (String) -> Unit ){
        navigator.navigateTo(Routes.SELECT_CAR)
    }
}