package com.alesh.baseproject.di.components

import android.app.Application
import com.alesh.baseproject.di.module.ApiModule
import com.alesh.baseproject.di.module.RepositoriesModule
import com.alesh.baseproject.ui.users.UserViewModel
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

    val userViewModel: UserViewModel
}