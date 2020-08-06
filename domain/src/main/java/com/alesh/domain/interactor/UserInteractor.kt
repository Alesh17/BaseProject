package com.alesh.domain.interactor

import com.alesh.domain.common.constant.SortingConstants
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.OwnResult
import com.alesh.domain.repository.UserRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun getUsers(): OwnResult<List<User>> {
        return repository.getUsers()
    }

    suspend fun getUsersBySort(sort: SortingConstants): OwnResult<List<User>> {
        return repository.getUsersBySort(sort)
    }

    suspend fun getUserById(id: Int): OwnResult<User> {
        return repository.getUserById(id)
    }
}