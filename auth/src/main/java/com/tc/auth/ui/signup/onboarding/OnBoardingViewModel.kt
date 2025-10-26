package com.tc.auth.ui.signup.onboarding

import androidx.lifecycle.ViewModel
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val navigator: AppNavigator
): ViewModel() {

    fun onSignInButtonPress(onSuccess: () -> Unit, onError: (String) -> Unit){
        navigator.navigateTo(Routes.SIGN_IN)
    }

}