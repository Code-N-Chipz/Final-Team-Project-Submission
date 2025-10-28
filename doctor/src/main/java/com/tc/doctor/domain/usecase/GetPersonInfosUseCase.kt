package com.tc.doctor.domain.usecase

import com.tc.doctor.data.mapper.toPersonInfo
import com.tc.tcmap.domain.PersonInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPersonInfosUseCase(
    private val getSpecialists: GetSpecialistsUseCase
) {
    operator fun invoke(): Flow<List<PersonInfo>> =
        // TODO: why cant we use our mapper list? because its flow?
        getSpecialists().map { list -> list.map { it.toPersonInfo() } }
}