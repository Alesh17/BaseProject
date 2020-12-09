package com.baseproject

import android.app.Application
import com.baseproject.di.components.AppComponent
import com.baseproject.di.components.DaggerAppComponent

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