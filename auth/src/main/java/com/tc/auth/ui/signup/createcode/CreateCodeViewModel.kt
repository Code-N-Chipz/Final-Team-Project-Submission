package com.tc.auth.ui.signup.createcode

import androidx.lifecycle.ViewModel
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CreateCodeViewModel @Inject constructor(
    private val navigator: AppNavigator,
): ViewModel() {

    val createdCode = MutableStateFlow(List(6) { "" })

    fun updateCreatedCode(index: Int, value: String) {
        createdCode.value = createdCode.value.toMutableList().apply { set(index, value) }
    }

    fun onSubmit(onSuccess : () -> Unit, onError:(String) -> Unit){
        navigator.navigateTo(Routes.CONFIRM_CODE)
    }
}