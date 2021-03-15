package com.baseproject.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baseproject.domain.error.ApplicationError
import com.baseproject.util.livedata.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Event<Boolean>>()
    val error = MutableLiveData<Event<ApplicationError>>()

    fun MutableLiveData<Event<Boolean>>.start() = this.postValue(Event(true))

    fun MutableLiveData<Event<Boolean>>.stop() = this.postValue(Event(false))

    fun CoroutineScope.launchWithLoading(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        loading.start()
        return launch(context, start, block).also { loading.stop() }
    }
}