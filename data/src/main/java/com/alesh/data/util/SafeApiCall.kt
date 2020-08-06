package com.alesh.data.util

import com.alesh.domain.error.ApplicationErrors
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
            is SocketTimeoutException -> OwnResult.Error(ApplicationErrors.TimeOut)
            is ConnectException       -> OwnResult.Error(ApplicationErrors.NoInternetConnection)
            is UnknownHostException   -> OwnResult.Error(ApplicationErrors.NoInternetConnection)
            is HttpException          -> {
                when (throwable.code()) {
                    HTTP_BAD_REQUEST    -> OwnResult.Error(ApplicationErrors.BadRequest)
                    HTTP_UNAUTHORIZED   -> OwnResult.Error(ApplicationErrors.Unauthorized)
                    HTTP_NOT_FOUND      -> OwnResult.Error(ApplicationErrors.NotFound)
                    HTTP_INTERNAL_ERROR -> OwnResult.Error(ApplicationErrors.Server)
                    else                -> OwnResult.Error(ApplicationErrors.Generic)
                }
            }
            else                      -> {
                OwnResult.Error(ApplicationErrors.Generic)
            }
        }
    }
}
