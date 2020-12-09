package com.baseproject.data.repository

import com.baseproject.data.mapper.mapToUser
import com.baseproject.data.mapper.mapToUsersList
import com.baseproject.data.model.pojo.user.UserFilterRequest
import com.baseproject.data.source.local.SharedPreferencesDataSource
import com.baseproject.data.source.remote.api.UserApi
import com.baseproject.data.util.safeApiCall
import com.baseproject.domain.common.constant.SortingConstants
import com.baseproject.domain.model.dto.User
import com.baseproject.domain.model.result.Result
import com.baseproject.domain.repository.UserRepository
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