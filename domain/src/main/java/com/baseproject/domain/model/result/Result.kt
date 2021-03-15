package com.baseproject.domain.model.result

import com.baseproject.domain.error.ApplicationError

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or an error with a value of type [ApplicationError].
 */
sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val error: ApplicationError) : Result<Nothing>()
}

/**
 * Converts the given value [T] to a result [Result.Success].
 */
fun <T> T.toSuccess() = Result.Success(this)

/**
 * Converts the given value [ApplicationError] to a result [Result.Error].
 */
fun ApplicationError.toError() = Result.Error(this)

/**
 * Returns the encapsulated value if this instance represents [Result.Success] or throw
 * ClassCastException if it is [Result.Error].
 */
fun <T> Result<T>.getOrException(): T {
    val success = this as Result.Success
    return success.value
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated value
 * if this instance represents [Result.Success] or the original encapsulated [ApplicationError] value
 * if it is [Result.Error].
 */
inline fun <R, T> Result<T>.map(transform: (value: T) -> R): Result<R> {
    return when (this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Error   -> Result.Error(this.error)
    }
}

/**
 * Performs the given [action] on the encapsulated value if this instance represents [Result.Success].
 * Returns the original `Result` unchanged.
 */
inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) action(value)
    return this
}

/**
 * Performs the given [action] on the encapsulated [ApplicationError] value if this instance represents [Result.Error].
 * Returns the original `Result` unchanged.
 */
inline fun <T> Result<T>.onError(action: (exception: ApplicationError) -> Unit): Result<T> {
    if (this is Result.Error) action(error)
    return this
}