package com.baseproject.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.baseproject.common.base.BaseViewModel
import com.baseproject.domain.interactor.UserInteractor
import com.baseproject.domain.model.dto.User
import com.baseproject.domain.model.result.onError
import com.baseproject.domain.model.result.onSuccess
import com.baseproject.util.livedata.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val interactor: UserInteractor
) : BaseViewModel() {

    val details = MutableLiveData<Event<User>>()
    val user = MutableLiveData<Event<List<User>>>()

    fun getUsers() {
        viewModelScope.launch {
            loading.start()
            interactor.getUsers()
                .onSuccess { user.postValue(Event(it)) }
                .onError { error.postValue(Event(it)) }
            loading.stop()
        }
    }

    fun openDetails(itemPosition: Int) {
        details.value = Event(user.value?.peekContent()!![itemPosition])
    }
}