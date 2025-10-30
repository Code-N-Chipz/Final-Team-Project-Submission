package com.tc.pcrepair.ui.search

import androidx.lifecycle.ViewModel
import com.tc.pcrepair.data.PCSearchData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class PCSearchViewModel () : ViewModel() {
    private val _uiState = MutableStateFlow(PCSearchData())
    val uiState: StateFlow<PCSearchData> = _uiState.asStateFlow()

    fun updateLocation(loc: String) { _uiState.value = _uiState.value.copy(location = loc) }
    fun updateDate(date: String) { _uiState.value = _uiState.value.copy(date = date) }
    fun updateType(type: String) { _uiState.value = _uiState.value.copy(type = type) }
    fun updateModel(model: String) { _uiState.value = _uiState.value.copy(model = model) }
    fun updateSearch(query: String) { _uiState.value = _uiState.value.copy(searchQuery = query) }

    fun calenderSelect(onSuccess : () -> Unit, onError: () -> Unit){


    }
}