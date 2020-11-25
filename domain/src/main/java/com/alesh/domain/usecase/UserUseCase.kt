package com.alesh.domain.usecase

import com.alesh.domain.common.constant.SortingConstants
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.Result
import com.alesh.domain.repository.UserRepository
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
}