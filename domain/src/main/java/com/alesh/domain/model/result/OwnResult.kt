package com.alesh.domain.model.result

import com.alesh.domain.error.ApplicationError

/**
 * The name [OwnResult] was chosen due to the fact that the Kotlin already has a class [Result]
 */
sealed class OwnResult<out T> {
    data class Success<out T>(val value: T) : OwnResult<T>()
    data class Error(val error: ApplicationError) : OwnResult<Nothing>()
}
