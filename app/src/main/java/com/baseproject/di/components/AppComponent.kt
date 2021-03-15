package com.baseproject.di.components

import android.app.Application
import com.baseproject.di.module.ApiModule
import com.baseproject.di.module.RepositoriesModule
import com.baseproject.ui.MainViewModel
import com.baseproject.ui.details.UserDetailsViewModel
import com.baseproject.ui.users.UserViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoriesModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    val mainViewModel: MainViewModel

    /* User flow */

    val userViewModel: UserViewModel
    val userDetailsViewModel: UserDetailsViewModel
}