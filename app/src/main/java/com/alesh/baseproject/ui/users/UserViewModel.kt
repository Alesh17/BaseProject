package com.alesh.baseproject.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alesh.baseproject.common.base.BaseViewModel
import com.alesh.baseproject.util.livedata.Event
import com.alesh.domain.interactor.UserInteractor
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.onError
import com.alesh.domain.model.result.onSuccess
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