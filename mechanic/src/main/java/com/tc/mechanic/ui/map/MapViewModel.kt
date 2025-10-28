package com.tc.mechanic.ui.map

import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val navigator: AppNavigator
) {
    fun submit(onSuccess: ()-> Unit, onError: (String) -> Unit ){
        navigator.navigateTo(Routes.SEARCH)
    }
}