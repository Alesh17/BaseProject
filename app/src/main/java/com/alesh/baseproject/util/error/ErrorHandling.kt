package com.alesh.baseproject.util.error

import com.alesh.baseproject.R
import com.alesh.domain.error.ApplicationError

fun ApplicationError.message() =

    when (this) {

        ApplicationError.Generic              -> R.string.generic_error
        ApplicationError.NoInternetConnection -> R.string.noInternetConnection_error

        ApplicationError.BadRequest           -> R.string.badRequest_error
        ApplicationError.NotFound             -> R.string.notFound_error
        ApplicationError.Unauthorized         -> R.string.unauthorized_error
        ApplicationError.TimeOut              -> R.string.timeout_error
        ApplicationError.Server               -> R.string.server_error
    }
