package com.tc.learn.data.model

data class MapMarker(
    val id: Int,
    val name: String,
    val imageUrl: String = "",   // Optional teacher profile picture
    val latitude: Double,
    val longitude: Double
)
