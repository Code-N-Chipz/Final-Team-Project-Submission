package com.tc.auth.ui.signup.verifyphone

import androidx.lifecycle.ViewModel
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class VerifyPhoneViewModel @Inject constructor(
    private val navigator: AppNavigator,
): ViewModel() {

    val verificationCode = MutableStateFlow(List(4) { "" })

    fun updateCode(index: Int, value: String) {
        verificationCode.value = verificationCode.value.toMutableList().apply {
            set(index, value)
        }
    }

    fun submit(onSuccess: ()-> Unit, onError: (String) -> Unit ){
        navigator.navigateTo(Routes.CREATE_CODE)
    }
}