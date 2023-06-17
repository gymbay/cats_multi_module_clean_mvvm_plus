package com.example.cats_clean_multi_module_mvvm.di

import com.example.network.api.ImagesApi
import com.example.network.providers.NetworkProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getProvider(): NetworkProvider = NetworkProvider()

    @Singleton
    @Provides
    fun getImagesApi(provider: NetworkProvider): ImagesApi = provider.createImagesApi()

}