package com.example.cats_clean_multi_module_mvvm

import android.app.Application
import com.example.cats_clean_multi_module_mvvm.di.AppComponent
import com.example.cats_clean_multi_module_mvvm.di.DaggerAppComponent

class MainApp: Application() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .setContext(this)
            .setResources(resources)
            .build()
    }

    override fun onCreate() {
        super.onCreate()


    }

}