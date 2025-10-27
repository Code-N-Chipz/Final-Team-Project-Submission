package com.tc.mechanic.ui.search

import androidx.lifecycle.ViewModel
import com.tc.mechanic.data.MechanicSearchData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MechanicSearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MechanicSearchData())
    val uiState: StateFlow<MechanicSearchData> = _uiState.asStateFlow()

    fun updateLocation(loc: String) { _uiState.value = _uiState.value.copy(location = loc) }
    fun updateDate(date: String) { _uiState.value = _uiState.value.copy(date = date) }
    fun updateType(type: String) { _uiState.value = _uiState.value.copy(type = type) }
    fun updateModel(model: String) { _uiState.value = _uiState.value.copy(model = model) }
    fun updateSearch(query: String) { _uiState.value = _uiState.value.copy(searchQuery = query) }
}