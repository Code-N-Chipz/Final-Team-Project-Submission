package com.tc.mechanic.data

data class MechanicFormState(
    val type: String = "Car",
    val model: String = "",
    val year: String = "",
    val motor: String = "",
    val availableTimes: List<Int> = listOf(8, 11, 14, 17, 20),
//    val selectedTime: Int = 14, todo: need to remove if slider worker
    val selectedTimeIndex: Int = 2, // index in availableTimes (default 14h)
    val isLoading: Boolean = false,
    val error: String? = null
)