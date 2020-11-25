package com.alesh.data.util

import com.alesh.domain.error.ApplicationError
import com.alesh.domain.model.result.OwnResult
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection.*
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): OwnResult<T> {
    return try {
        OwnResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException -> OwnResult.Error(ApplicationError.TimeOut)
            is ConnectException       -> OwnResult.Error(ApplicationError.NoInternetConnection)
            is UnknownHostException   -> OwnResult.Error(ApplicationError.NoInternetConnection)
            is HttpException          -> {
                when (throwable.code()) {
                    HTTP_BAD_REQUEST    -> OwnResult.Error(ApplicationError.BadRequest)
                    HTTP_UNAUTHORIZED   -> OwnResult.Error(ApplicationError.Unauthorized)
                    HTTP_NOT_FOUND      -> OwnResult.Error(ApplicationError.NotFound)
                    HTTP_INTERNAL_ERROR -> OwnResult.Error(ApplicationError.Server)
                    else                -> OwnResult.Error(ApplicationError.Generic)
                }
            }
            else                      -> {
                OwnResult.Error(ApplicationError.Generic)
            }
        }
    }
}
