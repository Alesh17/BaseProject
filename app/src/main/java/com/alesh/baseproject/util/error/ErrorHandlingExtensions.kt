package com.alesh.baseproject.util.error

import com.alesh.baseproject.R
import com.alesh.domain.error.ApplicationErrors

fun ApplicationErrors.message() =

    when (this) {

        ApplicationErrors.Generic              -> R.string.generic_error
        ApplicationErrors.NoInternetConnection -> R.string.noInternetConnection_error

        ApplicationErrors.BadRequest           -> R.string.badRequest_error
        ApplicationErrors.NotFound             -> R.string.notFound_error
        ApplicationErrors.Unauthorized         -> R.string.unauthorized_error
        ApplicationErrors.TimeOut              -> R.string.timeout_error
        ApplicationErrors.Server               -> R.string.server_error
    }
