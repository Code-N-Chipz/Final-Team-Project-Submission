package com.tc.auth.signup.createcode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CreateCodeViewModel: ViewModel() {

    val createdCode = MutableStateFlow(List(6) { "" })

    fun updateCreatedCode(index: Int, value: String) {
        createdCode.value = createdCode.value.toMutableList().apply { set(index, value) }
    }
}