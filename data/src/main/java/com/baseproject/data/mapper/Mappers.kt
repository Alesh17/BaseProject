package com.baseproject.data.mapper

import com.baseproject.data.model.pojo.user.UserResponse
import com.baseproject.domain.model.dto.User

fun UserResponse.mapToUser() = User(
    id = id,
    firstName = firstName,
    lastName = lastName
)

fun List<UserResponse>.mapToUsersList(): List<User> {
    return this.map {
        it.mapToUser()
    }
}