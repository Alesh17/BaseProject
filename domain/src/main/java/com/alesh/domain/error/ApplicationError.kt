package com.alesh.domain.error

sealed class ApplicationErrors {

    // Common errors

    object Generic : ApplicationErrors()
    object NoInternetConnection : ApplicationErrors()

    // Http errors

    object BadRequest : ApplicationErrors()
    object Unauthorized : ApplicationErrors()
    object NotFound : ApplicationErrors()
    object TimeOut : ApplicationErrors()
    object Server : ApplicationErrors()
}

