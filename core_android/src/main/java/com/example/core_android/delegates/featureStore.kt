package com.example.core_android.delegates

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController

abstract class FeatureStore : ViewModel()

inline fun <reified Store : FeatureStore> Fragment.featureStore(@IdRes navGraphId: Int) = lazy {
    val vmStore = findNavController().getViewModelStoreOwner(navGraphId)
    return@lazy ViewModelProvider(vmStore).get<Store>()
}