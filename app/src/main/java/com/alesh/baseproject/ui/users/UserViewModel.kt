package com.alesh.baseproject.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alesh.baseproject.util.livedata.Event
import com.alesh.domain.error.ApplicationError
import com.alesh.domain.interactor.UserInteractor
import com.alesh.domain.model.dto.User
import com.alesh.domain.model.result.OwnResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val interactor: UserInteractor
) : ViewModel() {

    val details = MutableLiveData<Event<User>>()
    val shipments = MutableLiveData<Event<List<User>>>()
    val error = MutableLiveData<Event<ApplicationError>>()
    val loading = MutableLiveData<Event<Boolean>>()

    fun getUsers() {
        viewModelScope.launch {
            when (val result = interactor.getUsers()) {
                is OwnResult.Success -> shipments.postValue(Event(result.value))
                is OwnResult.Error   -> error.postValue(Event(result.error))
            }
            loading.postValue(Event(false))
        }
    }

    fun openDetails(shipmentPosition: Int) {
        details.value = Event(shipments.value?.peekContent()!![shipmentPosition])
    }
}