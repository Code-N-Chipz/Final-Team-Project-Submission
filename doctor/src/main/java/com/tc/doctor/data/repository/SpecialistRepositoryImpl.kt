package com.tc.doctor.data.repository

import com.tc.doctor.domain.model.Specialist
import com.tc.doctor.domain.repository.SpecialistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpecialistRepositoryImpl(
    specialists: List<Specialist> = emptyList()
): SpecialistRepository {
    private val specialistsFlow = MutableStateFlow(specialists)

    override fun getSpecialists(): Flow<List<Specialist>> = specialistsFlow.asStateFlow()
}