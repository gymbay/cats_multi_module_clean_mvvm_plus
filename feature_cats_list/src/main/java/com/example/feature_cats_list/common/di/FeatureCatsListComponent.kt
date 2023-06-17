package com.example.feature_cats_list.common.di

import com.example.core.annotations.FeatureScope
import com.example.feature_cats_list.list.di.CatsListSubcomponent
import com.example.feature_cats_list.list.ui.CatsListFragment
import dagger.Component

@FeatureScope
@Component(dependencies = [FeatureCatsListDependencies::class])
internal interface FeatureCatsListComponent {

    @Component.Builder
    interface Builder {

        fun setDependencies(dependencies: FeatureCatsListDependencies): Builder

        fun build(): FeatureCatsListComponent

    }

    val catsListComponent: CatsListSubcomponent

}