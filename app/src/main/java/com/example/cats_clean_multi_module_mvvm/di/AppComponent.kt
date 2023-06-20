package com.example.cats_clean_multi_module_mvvm.di

import android.content.Context
import android.content.res.Resources
import com.example.cats_clean_multi_module_mvvm.di.data.DataModule
import com.example.cats_clean_multi_module_mvvm.di.domain.DomainModule
import com.example.cats_clean_multi_module_mvvm.di.network.NetworkDataSourcesModule
import com.example.cats_clean_multi_module_mvvm.di.network.NetworkModule
import com.example.core.annotations.AppScope
import com.example.feature_cats_list.common.di.FeatureCatsListDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [
    NetworkModule::class,
    DataModule::class,
    DomainModule::class,
    NetworkDataSourcesModule::class
])
interface AppComponent: FeatureCatsListDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        @BindsInstance
        fun setResources(resources: Resources): Builder

        fun build(): AppComponent

    }

}