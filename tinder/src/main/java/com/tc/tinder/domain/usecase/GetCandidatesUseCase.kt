package com.tc.tinder.domain.usecase

import com.tc.tinder.domain.repository.UserRepository
import com.tc.tinder.domain.model.userdetails.User
import kotlinx.coroutines.flow.Flow



class GetCandidatesUseCase(private val repo: UserRepository) {
    operator fun invoke(): Flow<List<User>> = repo.getCandidates()
}