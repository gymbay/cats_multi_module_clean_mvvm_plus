package com.example.feature_cats_details.common.di

import android.content.res.Resources
import com.example.domain_api.usecases.CatsUseCase
import kotlin.properties.Delegates

interface CatDetailsDependencies {

    val catsUseCase: CatsUseCase
    val resources: Resources

}

object CatDetailsDependenciesProvider {

    var dependencies: CatDetailsDependencies by Delegates.notNull()

}