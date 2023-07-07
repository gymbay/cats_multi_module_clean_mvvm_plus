package com.example.feature_cats_list.common.di

import com.example.core_android.providers.StringProvider
import com.example.domain_api.usecases.CatsUseCase
import kotlin.properties.Delegates

interface FeatureCatsListDependencies {

    val catsUseCase: CatsUseCase
    val stringProvider: StringProvider

}

object FeatureCatsListDependenciesProvider {

    var dependencies: FeatureCatsListDependencies by Delegates.notNull()

}