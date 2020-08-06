package com.alesh.data.mapper

import com.alesh.data.model.pojo.user.UserResponse
import com.alesh.domain.model.dto.User

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