package com.example.cats_clean_multi_module_mvvm.di.network

import com.example.core.annotations.AppScope
import com.example.network.api.ImagesApi
import com.example.network.providers.NetworkProvider
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun getProvider(): NetworkProvider = NetworkProvider()

    @AppScope
    @Provides
    fun getImagesApi(provider: NetworkProvider): ImagesApi = provider.createImagesApi()

}