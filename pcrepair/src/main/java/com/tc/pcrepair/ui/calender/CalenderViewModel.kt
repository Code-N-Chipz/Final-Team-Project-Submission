package com.tc.pcrepair.ui.calender

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tc.pcrepair.data.CalenderData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Locale

class CalenderViewModel(
    private val savedStateHandle: SavedStateHandle? = null
) : ViewModel() {
    private val _uiState = MutableStateFlow(CalenderData())
    val uiState: StateFlow<CalenderData> = _uiState.asStateFlow()

    init {
        // initialize with next 14 days and mock times
        val start = LocalDate.now()
        val days = (0 until 14).map { start.plusDays(it.toLong()) }
        _uiState.value = _uiState.value.copy(
            dates = days,
            selectedDateIndex = 0,
            timesForSelectedDate = generateTimesFor(days[0])
        )
    }

    private fun generateTimesFor(date: LocalDate): List<String> {
        // example: every 30 minutes from 13:00 to 17:00
        val list = mutableListOf<String>()
        for (h in 13..16) {
            list.add(String.format(Locale.US, "%02d:00 PM", if (h > 12) h - 12 else h))
            list.add(String.format(Locale.US, "%02d:30 PM", if (h > 12) h - 12 else h))
        }
        return list
    }

    fun selectDate(index: Int) {
        val safe = index.coerceIn(0, _uiState.value.dates.lastIndex)
        _uiState.value = _uiState.value.copy(
            selectedDateIndex = safe,
            timesForSelectedDate = generateTimesFor(_uiState.value.dates[safe]),
            selectedTimeIndex = null,
            error = null
        )
        savedStateHandle?.set("selected_date_index", safe)
    }

    fun selectTime(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTimeIndex = index)
        savedStateHandle?.set("selected_time_index", index)
    }

    fun confirmSelection(onSuccess: (LocalDate, String) -> Unit, onError: (String) -> Unit = {}) {
        val s = _uiState.value
        val timeIndex = s.selectedTimeIndex
        if (timeIndex == null) {
            _uiState.value = s.copy(error = "Please select a time slot")
            onError("Please select a time slot")
            return
        }
        viewModelScope.launch {
            _uiState.value = s.copy(loading = true, error = null)
            try {
                // simulate network / repo; immediately return success here
                onSuccess(s.dates[s.selectedDateIndex], s.timesForSelectedDate[timeIndex])
            } catch (e: Exception) {
                _uiState.value = s.copy(error = e.message ?: "Unknown error")
                onError(e.message ?: "Unknown error")
            } finally {
                _uiState.value = _uiState.value.copy(loading = false)
            }
        }
    }
}