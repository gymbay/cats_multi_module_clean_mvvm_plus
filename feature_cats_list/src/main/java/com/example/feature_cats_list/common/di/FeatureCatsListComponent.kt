package com.example.feature_cats_list.common.di

import com.example.core.annotations.Feature
import dagger.Component

@Feature
@Component(dependencies = [FeatureCatsListDependencies::class])
internal interface FeatureCatsListComponent {

    @Component.Builder
    interface Builder {

        fun setDependencies(dependencies: FeatureCatsListDependencies)

        fun build(): FeatureCatsListComponent

    }

}