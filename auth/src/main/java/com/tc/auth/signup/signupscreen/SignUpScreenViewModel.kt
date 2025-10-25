package com.tc.auth.signup.signupscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class SignUpScreenViewModel : ViewModel() {

//    todo: might need to make this all in to Data module. wait untill
    open val firstName = MutableStateFlow("")
    open val lastName = MutableStateFlow("")
    open val gender = MutableStateFlow("Male")
    open val email = MutableStateFlow("")
    open val phone = MutableStateFlow("")


}

