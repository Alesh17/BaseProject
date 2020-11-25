package com.alesh.domain.repository

import com.alesh.domain.common.constant.SortingConstants
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.Result

interface UserRepository {

    suspend fun getUsers(): Result<List<User>>

    suspend fun getUsersBySort(sort: SortingConstants): Result<List<User>>

    suspend fun getUserById(id: Int): Result<User>

    fun saveUserId(id: Int)
}