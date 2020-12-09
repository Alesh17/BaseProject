package com.baseproject.data.source.remote.api

import com.baseproject.data.model.pojo.user.UserFilterRequest
import com.baseproject.data.model.pojo.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/api/users")
    suspend fun getUsers(): List<UserResponse>

    @GET("/api/users")
    suspend fun getUsersByFilter(@Body filterRequest: UserFilterRequest): List<UserResponse>

    @GET("/api/users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserResponse
}