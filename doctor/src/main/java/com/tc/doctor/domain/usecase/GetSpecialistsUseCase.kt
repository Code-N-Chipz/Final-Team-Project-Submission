package com.tc.doctor.domain.usecase

import com.tc.doctor.domain.model.Specialist
import com.tc.doctor.domain.repository.SpecialistRepository
import kotlinx.coroutines.flow.Flow

class GetSpecialistsUseCase(
    private val repository: SpecialistRepository
) {
    operator fun invoke(): Flow<List<Specialist>> = repository.getSpecialists()
}