package com.tc.mechanic.ui.selectcar

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.auth.ui.navigation.AppNavigator
import com.tc.auth.ui.navigation.Routes
import com.tc.mechanic.data.MechanicFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MechanicCarSelectorViewModel @Inject constructor(
    private val navigator: AppNavigator? = null,
    private val savedStateHandle: SavedStateHandle? = null //  inject via Hilt in real app
): ViewModel() {
    private val _uiState = MutableStateFlow(MechanicFormState())
    val uiState: StateFlow<MechanicFormState> = _uiState.asStateFlow()

    fun updateType(type: String) {
        _uiState.value = _uiState.value.copy(type = type)
        savedStateHandle?.set("mechanic_type", type)
    }

    fun updateModel(model: String) {
        _uiState.value = _uiState.value.copy(model = model)
        savedStateHandle?.set("mechanic_model", model)
    }

    fun updateYear(year: String) {
        _uiState.value = _uiState.value.copy(year = year)
        savedStateHandle?.set("mechanic_year", year)
    }

    fun updateMotor(motor: String) {
        _uiState.value = _uiState.value.copy(motor = motor)
        savedStateHandle?.set("mechanic_motor", motor)
    }

//    used in screen1
//    fun selectTime(time: Int) {
//        _uiState.value = _uiState.value.copy(selectedTime = time)
//        savedStateHandle?.set("mechanic_selected_time", time)
//    }

    fun selectTimeIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTimeIndex = index)
        savedStateHandle?.set("mechanic_selected_time_index", index)
    }

    private fun validate(): Boolean {
        val s = _uiState.value
        val missing = when {
            s.model.isBlank() -> "Please enter model"
            s.year.isBlank() -> "Please enter year"
            s.motor.isBlank() -> "Please enter motor"
            else -> null
        }
        _uiState.value = s.copy(error = missing)
        return missing == null
    }

    fun submit(onSuccess: (MechanicFormState) -> Unit = {}, onError: (String) -> Unit = {}) {
        if (!validate()) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                // TODO: repository/network call
                onSuccess(_uiState.value)
                navigator?.navigateTo(Routes.FILTER)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message ?: "Unknown error")
                onError(e.message ?: "Unknown error")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}