package com.tc.auth.signup.verifyphone

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class VerifyPhoneViewModel: ViewModel() {

    val verificationCode = MutableStateFlow(List(4) { "" })

    fun updateCode(index: Int, value: String) {
        verificationCode.value = verificationCode.value.toMutableList().apply { set(index, value) }
    }
}