package com.alesh.data.repository

import com.alesh.data.mapper.mapToUser
import com.alesh.data.mapper.mapToUsersList
import com.alesh.data.model.pojo.user.UserFilterRequest
import com.alesh.data.source.local.SharedPreferencesDataSource
import com.alesh.data.source.remote.api.UserApi
import com.alesh.data.util.safeApiCall
import com.alesh.domain.common.constant.SortingConstants
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.Result
import com.alesh.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi,
    private val sharedPrefs: SharedPreferencesDataSource
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return safeApiCall { api.getUsers().mapToUsersList() }
    }

    override suspend fun getUsersBySort(sort: SortingConstants): Result<List<User>> {
        val request = UserFilterRequest(sort.value)
        return safeApiCall { api.getUsersByFilter(request).mapToUsersList() }
    }

    override suspend fun getUserById(id: Int): Result<User> {
        return safeApiCall { api.getUserById(id).mapToUser() }
    }

    override fun saveUserId(id: Int) {
        sharedPrefs.setUserId(id)
    }
}