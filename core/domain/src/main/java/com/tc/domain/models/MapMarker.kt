package com.tc.domain.models

// New Changes

data class MapMarker (
    val id: String,
    val title: String,
    val snippet: String?,
    val latitude: Double,
    val longitude: Double
)