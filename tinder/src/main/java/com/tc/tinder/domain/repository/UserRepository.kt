package com.tc.tinder.domain.repository

import com.tc.tinder.domain.model.userdetails.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCandidates(): Flow<List<User>>
}