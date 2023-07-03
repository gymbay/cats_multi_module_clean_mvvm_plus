package com.example.feature_cats_details.common.di

import com.example.core_android.delegates.FeatureStore

internal class CatDetailsStore : FeatureStore() {

    val featureComponent by lazy {
        DaggerCatDetailsComponent
            .builder()
            .setDependencies(CatDetailsDependenciesProvider.dependencies)
            .build()
    }

}