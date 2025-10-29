package com.tc.pcrepair.data


data class PCSearchData(
    val location: String = "Johannesburg, 1 Road Ubuntu",
    val date: String = "20 Mar - 10h",
    val type: String = "Car",
    val model: String = "Lexus",
    val searchQuery: String = "",
    val favorites: List<String> = emptyList()
)