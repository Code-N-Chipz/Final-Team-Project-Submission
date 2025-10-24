package com.tc.auth.signup.signupscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class SignUpScreenViewModel : ViewModel() {

    open val firstName = MutableStateFlow("")
    open val lastName = MutableStateFlow("")
    open val gender = MutableStateFlow("Male")
    open val email = MutableStateFlow("")
    open val phone = MutableStateFlow("")

    /**
     * This section need into the other ViewModels
     */
//    val verificationCode = MutableStateFlow(List(6) { "" })
//    val createdCode = MutableStateFlow(List(6) { "" })
//
//    fun updateCode(index: Int, value: String) {
//        verificationCode.value = verificationCode.value.toMutableList().apply { set(index, value) }
//    }
//
//    fun updateCreatedCode(index: Int, value: String) {
//        createdCode.value = createdCode.value.toMutableList().apply { set(index, value) }
//    }
}

