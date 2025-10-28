package com.tc.mechanic.ui.filter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tc.mechanic.data.FiltersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilterViewModel (
    private val savedStateHandle: SavedStateHandle? = null) : ViewModel() {
    private val _uiState = MutableStateFlow(FiltersUiState())
    val uiState: StateFlow<FiltersUiState> = _uiState.asStateFlow()

    fun setSort(sort: String) {
        _uiState.value = _uiState.value.copy(sortBy = sort)
        savedStateHandle?.set("filters_sort", sort)
    }

//    fun setPrice(value: Float) {
//        _uiState.value = _uiState.value.copy(pricePerHour = value)
//        savedStateHandle?.set("filters_price", value)
//    }

    fun selectPricePerHour(index: Int) {
        _uiState.value = _uiState.value.copy(selectedPricePerHourIndex = index)
        savedStateHandle?.set("filter_price_per_hour", index)
    }

    fun setRating(value: Int) {
        _uiState.value = _uiState.value.copy(rating = value.coerceIn(0, 5))
        savedStateHandle?.set("filters_rating", value)
    }

    fun apply(onApplied: (FiltersUiState) -> Unit) {
        onApplied(_uiState.value)
//        navigator?.navigateTo(Routes.MAP)
    }
}