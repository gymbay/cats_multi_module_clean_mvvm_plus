package com.example.cats_clean_multi_module_mvvm.di.modules.network

import com.example.network.data_sources.ImagesDataSourceImpl
import com.example.network_api.data_sources.ImagesDataSource
import dagger.Binds
import dagger.Module

@Module
interface NetworkDataSourcesModule {

    @Binds
    fun bindImagesDataSource(imagesDataSourceImpl: ImagesDataSourceImpl): ImagesDataSource

}