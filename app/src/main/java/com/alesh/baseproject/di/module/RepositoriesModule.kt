package com.alesh.baseproject.di.module

import com.alesh.data.repository.UserRepositoryImpl
import com.alesh.data.source.local.SharedPreferencesDataSource
import com.alesh.data.source.remote.api.UserApi
import com.alesh.domain.repository.UserRepository
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