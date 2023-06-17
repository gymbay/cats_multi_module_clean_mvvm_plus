package com.example.feature_cats_list.common.di

import com.example.domain_api.models.usecases.CatsUseCase
import kotlin.properties.Delegates

interface FeatureCatsListDependencies {

    val catsUseCase: CatsUseCase

}

object FeatureCatsListDependenciesStore {

    var dependencies: FeatureCatsListDependencies by Delegates.notNull()

}