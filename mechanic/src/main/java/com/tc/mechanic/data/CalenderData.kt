package com.tc.mechanic.data

import java.time.LocalDate


data class CalenderData(
    val dates: List<LocalDate> = emptyList(),
    val selectedDateIndex: Int = 0,
    val timesForSelectedDate: List<String> = emptyList(),
    val selectedTimeIndex: Int? = null,
    val loading: Boolean = false,
    val error: String? = null
)