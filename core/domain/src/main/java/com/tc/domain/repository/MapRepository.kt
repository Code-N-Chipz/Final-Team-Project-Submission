package com.tc.domain.repository

import com.tc.domain.models.MapMarker

interface MapRepository {
    suspend fun getSavedMarkers(): List<MapMarker>
    suspend fun getLastKnownLocation(): Pair<Double, Double>? // lat,lng or null
}