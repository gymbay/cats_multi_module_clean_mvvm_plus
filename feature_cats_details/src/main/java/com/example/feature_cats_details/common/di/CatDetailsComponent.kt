package com.example.feature_cats_details.common.di

import com.example.core.annotations.FeatureScope
import com.example.feature_cats_details.details.ui.CatDetailsViewModel
import dagger.Component

@FeatureScope
@Component(dependencies = [CatDetailsDependencies::class])
internal interface CatDetailsComponent {

    val detailsViewModel: CatDetailsViewModel.Factory

    @Component.Builder
    interface Builder {

        fun setDependencies(dependencies: CatDetailsDependencies): Builder

        fun build(): CatDetailsComponent

    }

}