package com.example.cats_clean_multi_module_mvvm.di.modules.data

import com.example.data.repositories.CatsRepositoryImpl
import com.example.data_api.repositories.CatsRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindCatsRepository(repositoryImpl: CatsRepositoryImpl): CatsRepository

}