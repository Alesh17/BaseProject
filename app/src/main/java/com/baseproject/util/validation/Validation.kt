package com.baseproject.util.validation

import com.baseproject.domain.error.ApplicationError
import com.baseproject.domain.model.result.Result
import com.baseproject.domain.model.result.toSuccess

fun String.startCheck() = this.toSuccess()

fun Result<String>.checkEmpty(): Result<String> {
    return if (this is Result.Success) {
        if (value.isNotEmpty()) Result.Success(value)
        else Result.Error(ApplicationError.EmptyFiled)
    } else this
}

private fun String.matchTo(regExp: String) = matches(regExp.toRegex())

private fun String.isEmailValid() = this.matchTo(EMAIL)