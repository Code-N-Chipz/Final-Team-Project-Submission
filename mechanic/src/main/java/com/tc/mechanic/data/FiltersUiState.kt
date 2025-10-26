package com.tc.mechanic.data

data class FiltersUiState(
    val sortBy: String = "Recommend",
//    val pricePerHour: Float = 30f,
    val availablePricePerHour: List<Int> = listOf(0,30,60),
    val selectedPricePerHourIndex: Int = 1,
    val rating: Int = 4 // 0..5
)