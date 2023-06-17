package com.example.feature_cats_list.common.di

import com.example.core_android.delegates.FeatureStore

internal class FeatureCatsListStore : FeatureStore() {

    val featureComponent by lazy {
        DaggerFeatureCatsListComponent
            .builder()
            .setDependencies(FeatureCatsListDependenciesProvider.dependencies)
            .build()
    }

    val catsListSubcomponent by lazy {
        featureComponent.catsListComponent
    }

}