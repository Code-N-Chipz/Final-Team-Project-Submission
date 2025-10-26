package com.tc.auth.ui.signup.confirmcode

import androidx.lifecycle.ViewModel
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import com.tc.auth.ui.signup.createcode.CreateCodeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Route
import javax.inject.Inject

class ConfirmCodeViewModel @Inject constructor(
    private val navigator: AppNavigator
): ViewModel() {

    val confirmedCode = MutableStateFlow(List(6) { "" })
    val createCode = CreateCodeViewModel(navigator).createdCode.value

    fun updateConfirmedCode(index: Int, value: String) {
        confirmedCode.value = confirmedCode.value.toMutableList().apply { set(index, value) }
    }

//    fun checkCodeWithCreate(){
//
//        if (confirmedCode.value == createCode){
//            onSubmit( )
//        }
//
//    }

    fun onSubmit(onSuccess: () -> Unit, onError: (String) -> Unit){
        navigator.navigateTo(Routes.ON_BOARDING)
    }
}