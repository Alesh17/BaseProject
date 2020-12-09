package com.baseproject.di.module

import com.baseproject.data.source.remote.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module(includes = [HttpModule::class])
object ApiModule {

    @Reusable
    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)
}