package com.tc.auth.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.network.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigator: AppNavigator
) : ViewModel() {

    private val _signInResult = MutableLiveData<Boolean>()
    val signInResult: LiveData<Boolean> = _signInResult
    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> = _signUpResult

    fun onItemClick(id: String) = navigator.navigateTo("details?id=$id")

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val success = authRepository.signInWithEmailAndPassword(email, password) // suspend function
                _signInResult.value = success
            } catch (e: Exception) {
                _signInResult.value = false
            }
        }
    }
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val success = authRepository.createUserWithEmailAndPassword(email, password) // suspend function
                _signUpResult.value = success
            } catch (e: Exception) {
                _signUpResult.value = false
            }
        }
    }
}


