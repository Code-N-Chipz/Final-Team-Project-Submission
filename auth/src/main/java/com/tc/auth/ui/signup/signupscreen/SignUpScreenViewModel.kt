package com.tc.auth.ui.signup.signupscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SignUpData(
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "Male",
    val email: String = "",
    val phone: String = "",
    val error:String? = null,
    val isLoading : Boolean = false
)
class SignUpScreenViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpData())
    val uiState: StateFlow<SignUpData> = _uiState

    fun updateFirstName(firstName: String) {
        _uiState.value = _uiState.value.copy(firstName = firstName)
        savedStateHandle["signup_first_name"] = firstName
    }

    fun updateLastName(lastName: String) {
        _uiState.value = _uiState.value.copy(lastName = lastName)
        savedStateHandle["signup_last_name"] = lastName
    }

    fun updateGender(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
        savedStateHandle["signup_gender"] = gender
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(gender = email)
        savedStateHandle["signup_email"] = email
    }

    fun updatePhoneNumber(phone: String) {
        _uiState.value = _uiState.value.copy(phone = phone)
        savedStateHandle["signup_phone"] = phone
    }

//Validating the values
    private fun validate(): Boolean {
        val state = _uiState.value
        var ok = true
        var emailErr: String? = null
        var phoneErr: String? = null

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            emailErr = "Invalid email"
            ok = false
        }
        else if (!android.util.Patterns.PHONE.matcher(state.phone).matches()) {
            phoneErr = "Invalid phone"
            ok = false
        }

        _uiState.value = state.copy(error = emailErr ?: phoneErr)
        return ok
    }

    fun submit(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (!validate()) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // call repository to sign up...
                // repository.signUp(state.email, state.password)
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Unknown error")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}



