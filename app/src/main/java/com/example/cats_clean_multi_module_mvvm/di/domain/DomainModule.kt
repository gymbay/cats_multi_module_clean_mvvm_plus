package com.example.cats_clean_multi_module_mvvm.di.domain

import com.example.domain.usecases.CatsUseCaseImpl
import com.example.domain_api.models.usecases.CatsUseCase
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindCatsUseCase(useCaseImpl: CatsUseCaseImpl): CatsUseCase

}