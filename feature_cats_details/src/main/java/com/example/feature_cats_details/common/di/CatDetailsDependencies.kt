package com.example.feature_cats_details.common.di

import com.example.core_android.providers.StringProvider
import com.example.domain_api.usecases.CatsUseCase
import kotlin.properties.Delegates

interface CatDetailsDependencies {

    val catsUseCase: CatsUseCase
    val stringProvider: StringProvider

}

object CatDetailsDependenciesProvider {

    var dependencies: CatDetailsDependencies by Delegates.notNull()

}