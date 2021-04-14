package com.baseproject.domain.repository

import com.baseproject.domain.common.constant.SortingConstants
import com.baseproject.domain.model.dto.User
import com.baseproject.domain.model.result.Result
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Result<List<User>>

    suspend fun getUsersBySort(sort: SortingConstants): Result<List<User>>

    suspend fun getUserById(id: Int): Result<User>

    fun getUserStatus(): Flow<Result<Boolean>>

    fun saveUserId(id: Int)
}