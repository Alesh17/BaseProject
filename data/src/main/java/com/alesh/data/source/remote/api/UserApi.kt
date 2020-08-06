package com.alesh.data.source.remote.api

import com.alesh.data.model.pojo.user.UserFilterRequest
import com.alesh.data.model.pojo.user.UserResponse
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