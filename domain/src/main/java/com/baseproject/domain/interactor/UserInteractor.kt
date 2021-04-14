package com.baseproject.domain.interactor

import com.baseproject.domain.common.constant.SortingConstants
import com.baseproject.domain.model.dto.User
import com.baseproject.domain.model.result.Result
import com.baseproject.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val userUseCase: UserUseCase
) {

    suspend fun getUsers(): Result<List<User>> {
        return userUseCase.getUsers()
    }

    suspend fun getUsersBySort(sort: SortingConstants): Result<List<User>> {
        return userUseCase.getUsersBySort(sort)
    }

    suspend fun getUserById(id: Int): Result<User> {
        return userUseCase.getUserById(id)
    }

    fun getUserStatus(): Flow<Result<Boolean>> {
        return userUseCase.getUserStatus()
    }
}