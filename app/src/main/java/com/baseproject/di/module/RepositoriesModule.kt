package com.baseproject.di.module

import com.baseproject.data.repository.UserRepositoryImpl
import com.baseproject.data.source.local.SharedPreferencesDataSource
import com.baseproject.data.source.remote.api.UserApi
import com.baseproject.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoriesModule {

    @Provides
    fun provideUserRepository(
        api: UserApi,
        sharedPrefs: SharedPreferencesDataSource
    ): UserRepository = UserRepositoryImpl(api, sharedPrefs)
}