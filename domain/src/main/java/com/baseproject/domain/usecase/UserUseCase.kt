package com.baseproject.domain.usecase

import com.baseproject.domain.common.constant.SortingConstants
import com.baseproject.domain.model.dto.User
import com.baseproject.domain.model.result.Result
import com.baseproject.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun getUsers(): Result<List<User>> {
        return repository.getUsers()
    }

    suspend fun getUsersBySort(sort: SortingConstants): Result<List<User>> {
        return repository.getUsersBySort(sort)
    }

    suspend fun getUserById(id: Int): Result<User> {
        return repository.getUserById(id)
    }

    fun getUserStatus(): Flow<Result<Boolean>> {
        return repository.getUserStatus()
    }
}