package com.example.cats_clean_multi_module_mvvm.di

import android.content.Context
import android.content.res.Resources
import com.example.cats_clean_multi_module_mvvm.di.data.DataModule
import com.example.cats_clean_multi_module_mvvm.di.domain.DomainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    DataModule::class,
    DomainModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        @BindsInstance
        fun setResources(resources: Resources): Builder

        fun build(): AppComponent

    }

}