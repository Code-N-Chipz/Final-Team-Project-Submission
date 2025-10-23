package com.tc.domain.usecase

import com.tc.domain.models.MapMarker
import com.tc.domain.repository.MapRepository

class GetSavedMarkersUseCase(private val repository: MapRepository) {
    suspend operator fun invoke(): List<MapMarker> = repository.getSavedMarkers()
}