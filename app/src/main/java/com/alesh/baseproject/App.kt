package com.alesh.baseproject

import android.app.Application
import com.alesh.baseproject.di.components.AppComponent
import com.alesh.baseproject.di.components.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var component: AppComponent
            private set
    }
}