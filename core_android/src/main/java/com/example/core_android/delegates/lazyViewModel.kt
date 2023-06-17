package com.example.core_android.delegates

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline createBlock: () -> T
) = viewModels<T>(
    factoryProducer = {
        ViewModelFactory(createBlock)
    }
)


class ViewModelFactory(
    private val createBlock: () -> ViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return createBlock() as T
    }

}