package com.tc.tinder.data.repository



import com.tc.tinder.domain.model.userdetails.User
import com.tc.tinder.domain.repository.UserRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserFakeRepositoryImpl(
    initialUsers: List<User> = emptyList()
) : UserRepository {

    private val usersFlow = MutableStateFlow(initialUsers)

    /** Stream all candidate users. */
    override fun getCandidates(): Flow<List<User>> = usersFlow.asStateFlow()


}