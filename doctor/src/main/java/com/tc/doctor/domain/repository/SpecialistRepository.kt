package com.tc.doctor.domain.repository

import com.tc.doctor.domain.model.Specialist
import kotlinx.coroutines.flow.Flow

interface SpecialistRepository {
    fun getSpecialists(): Flow<List<Specialist>>
}