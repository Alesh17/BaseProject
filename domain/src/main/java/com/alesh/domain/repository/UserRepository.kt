package com.alesh.domain.repository

import com.alesh.domain.common.constant.SortingConstants
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.OwnResult

interface UserRepository {

    suspend fun getUsers(): OwnResult<List<User>>

    suspend fun getUsersBySort(sort: SortingConstants): OwnResult<List<User>>

    suspend fun getUserById(id: Int): OwnResult<User>

    fun saveUserId(id: Int)
}