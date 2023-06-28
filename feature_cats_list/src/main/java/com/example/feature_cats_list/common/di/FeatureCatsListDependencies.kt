package com.example.feature_cats_list.common.di

import android.content.res.Resources
import com.example.domain_api.usecases.CatsUseCase
import kotlin.properties.Delegates

interface FeatureCatsListDependencies {

    val catsUseCase: CatsUseCase
    val resources: Resources

}

object FeatureCatsListDependenciesProvider {

    var dependencies: FeatureCatsListDependencies by Delegates.notNull()

}