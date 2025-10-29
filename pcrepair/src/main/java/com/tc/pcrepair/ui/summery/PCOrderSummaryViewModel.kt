package com.tc.pcrepair.ui.summery

import androidx.lifecycle.ViewModel
import com.tc.pcrepair.data.OrderSummaryData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PCOrderSummaryViewModel (
) : ViewModel() {
    private val _uiState = MutableStateFlow(OrderSummaryData())
    val uiState: StateFlow<OrderSummaryData> = _uiState.asStateFlow()

    fun updateHours(h: Int) { _uiState.value = _uiState.value.copy(hours = h.coerceAtLeast(1)) }
    fun placeOrder(onSuccess: () -> Unit) {
        // simulate order placement
        onSuccess()
    }
}