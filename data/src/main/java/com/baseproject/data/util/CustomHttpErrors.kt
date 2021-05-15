package com.baseproject.data.util

import com.baseproject.data.model.pojo.common.error.ErrorResponse
import com.baseproject.domain.error.ApplicationError
import com.google.gson.Gson
import retrofit2.HttpException

fun HttpException.getCustomHttpError(): Int? {
    return try {
        this.response()?.errorBody()?.charStream()?.let {
            Gson().fromJson(it, ErrorResponse::class.java).error
        }
    } catch (exception: Exception) {
        null
    }
}

fun Int?.getApplicationErrorOrNull() = when (this) {
    1    -> ApplicationError.EmptyFiled
    else -> null
}