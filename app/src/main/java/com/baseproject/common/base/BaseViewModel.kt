package com.baseproject.common.base

import androidx.lifecycle.ViewModel
import com.baseproject.domain.error.ApplicationError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {

    val loading = MutableSharedFlow<Boolean>(replay = 1)
    val error = MutableSharedFlow<ApplicationError>(replay = 1)

    suspend fun MutableSharedFlow<Boolean>.start() = this.emit(true)

    suspend fun MutableSharedFlow<Boolean>.stop() = this.emit(false)

    fun CoroutineScope.launchWithLoading(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return launch(context, start) {
            loading.start()
            block()
            loading.stop()
        }
    }
}