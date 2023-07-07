package com.example.cats_clean_multi_module_mvvm.di.modules

import com.example.core_android.providers.StringProvider
import com.example.core_android.providers.StringProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface ProvidersModule {

    @Binds
    fun bindStringProvider(stringProvider: StringProviderImpl): StringProvider

}