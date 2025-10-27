package com.tc.pcrepair.ui.selectcar

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.pcrepair.data.PcRepairFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PcRepairSelectorViewModel(
    private val savedStateHandle: SavedStateHandle? = null //  inject via Hilt in real app
): ViewModel() {
    private val _uiState = MutableStateFlow(PcRepairFormState())
    val uiState: StateFlow<PcRepairFormState> = _uiState.asStateFlow()

    fun updateType(type: String) {
        _uiState.value = _uiState.value.copy(type = type)
        savedStateHandle?.set("mechanic_type", type)
    }

    fun updateProblem(model: String) {
        _uiState.value = _uiState.value.copy(problem = model)
        savedStateHandle?.set("mechanic_model", model)
    }

    fun selectTimeIndex(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTimeIndex = index)
        savedStateHandle?.set("mechanic_selected_time_index", index)
    }

    private fun validate(): Boolean {
        val s = _uiState.value
        val missing = when {
            s.type.isBlank() -> "Please enter type"
            s.problem.isBlank() -> "Please enter problem"
            else -> null
        }
        _uiState.value = s.copy(error = missing)
        return missing == null
    }

    fun submit(onSuccess: (PcRepairFormState) -> Unit = {}, onError: (String) -> Unit = {}) {
        if (!validate()) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                // TODO: repository/network call
                onSuccess(_uiState.value)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message ?: "Unknown error")
                onError(e.message ?: "Unknown error")
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}