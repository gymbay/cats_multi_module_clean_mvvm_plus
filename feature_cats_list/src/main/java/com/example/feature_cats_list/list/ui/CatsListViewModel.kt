package com.example.feature_cats_list.list.ui

import androidx.lifecycle.viewModelScope
import com.example.core_android.architecture.BaseViewModel
import com.example.domain_api.models.request.CatsFilter
import com.example.domain_api.models.request.Order
import com.example.domain_api.models.response.CatModel
import com.example.domain_api.models.usecases.CatsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatsListViewModel @Inject constructor(
    private val catsUseCase: CatsUseCase
) : BaseViewModel<CatsListViewModel.State, CatsListViewModel.Actions>(State()) {

    fun prepare() {
        viewModelScope.launch {
            val cats = catsUseCase.getCats(CatsFilter(5, 1, Order.DESC))
            println("Casts size ${cats.size}")
        }
    }

    data class State(
        val cats: List<CatModel> = emptyList()
    )

    sealed class Actions {

    }

}