package com.baseproject.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baseproject.util.livedata.Event
import com.baseproject.domain.error.ApplicationError

open class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Event<Boolean>>()
    val error = MutableLiveData<Event<ApplicationError>>()

    fun MutableLiveData<Event<Boolean>>.start() = this.postValue(Event(true))

    fun MutableLiveData<Event<Boolean>>.stop() = this.postValue(Event(false))

    suspend fun withLoading(block: suspend () -> Unit) {
        loading.start()
        block()
        loading.stop()
    }
}