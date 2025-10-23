package com.tc.domain.usecase

import com.tc.domain.repository.MapRepository

class GetLastKnownLocationUseCase(private val repository: MapRepository) {
    suspend operator fun invoke(): Pair<Double, Double>? = repository.getLastKnownLocation()
}