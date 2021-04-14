package com.baseproject.ui.users

import androidx.lifecycle.viewModelScope
import com.baseproject.common.base.BaseViewModel
import com.baseproject.domain.interactor.UserInteractor
import com.baseproject.domain.model.dto.User
import com.baseproject.domain.model.result.onError
import com.baseproject.domain.model.result.onSuccess
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val interactor: UserInteractor
) : BaseViewModel() {

    val users = MutableSharedFlow<List<User>>(replay = 1)
    val status = MutableSharedFlow<Boolean>(replay = 1)

    fun getUsers() {
        viewModelScope.launchWithLoading {
            interactor.getUsers()
                .onSuccess { users.emit(it) }
                .onError { error.emit(it) }
        }
    }

    fun getUserStatus() {
        viewModelScope.launch {
            interactor.getUserStatus().collect { statusResult ->
                statusResult
                    .onSuccess { status.emit(it) }
                    .onError { error.emit(it) }
            }
        }
    }
}