package com.example.cats_clean_multi_module_mvvm.di

import android.content.Context
import android.content.res.Resources
import com.example.cats_clean_multi_module_mvvm.di.modules.ProvidersModule
import com.example.cats_clean_multi_module_mvvm.di.modules.data.DataModule
import com.example.cats_clean_multi_module_mvvm.di.modules.domain.DomainModule
import com.example.cats_clean_multi_module_mvvm.di.modules.network.NetworkDataSourcesModule
import com.example.cats_clean_multi_module_mvvm.di.modules.network.NetworkModule
import com.example.core.annotations.AppScope
import com.example.feature_cats_details.common.di.CatDetailsDependencies
import com.example.feature_cats_list.common.di.FeatureCatsListDependencies
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [
    NetworkModule::class,
    NetworkDataSourcesModule::class,
    DataModule::class,
    DomainModule::class,
    ProvidersModule::class
])
interface AppComponent: FeatureCatsListDependencies, CatDetailsDependencies {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        @BindsInstance
        fun setResources(resources: Resources): Builder

        fun build(): AppComponent

    }

}