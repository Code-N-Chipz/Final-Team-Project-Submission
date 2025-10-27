package com.tc.pcrepair.data

data class PcRepairFormState(
    val type: String = "Laptop",
    val problem: String = "",
//    val year: String = "",
//    val model: String = "",
    val availableTimes: List<Int> = listOf(8, 11, 14, 17, 20),
//    val selectedTime: Int = 14, todo: need to remove if slider worker
    val selectedTimeIndex: Int = 2, // index in availableTimes (default 14h)
    val isLoading: Boolean = false,
    val error: String? = null
)