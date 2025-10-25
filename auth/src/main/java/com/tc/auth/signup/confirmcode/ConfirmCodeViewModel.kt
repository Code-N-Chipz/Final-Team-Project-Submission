package com.tc.auth.signup.confirmcode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ConfirmCodeViewModel: ViewModel() {

    val confirmedCode = MutableStateFlow(List(6) { "" })

    fun updateConfirmedCode(index: Int, value: String) {
        confirmedCode.value = confirmedCode.value.toMutableList().apply { set(index, value) }
    }
}