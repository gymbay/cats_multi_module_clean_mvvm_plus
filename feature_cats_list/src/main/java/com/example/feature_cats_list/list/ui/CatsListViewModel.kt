package com.example.feature_cats_list.list.ui

import androidx.lifecycle.viewModelScope
import com.example.core_android.architecture.BaseViewModel
import com.example.domain_api.models.usecases.CatsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CatsListViewModel @Inject constructor(
    private val catsUseCase: CatsUseCase
) : BaseViewModel<CatsListViewModel.UiState, CatsListViewModel.Actions>(UiState()) {

    private var currentPage: Int = 0
    private val limit: Int = 20
    private var allCatsLoaded: Boolean = false

    init {
        nextPage()
    }

    private fun nextPage() {
        if (allCatsLoaded) return

        viewModelScope.launch {
            modifyState { copy(isLoading = true) }

            try {
                val currentCats = getState().cats
                val newCats = catsUseCase.getCats(
                    com.example.domain_models.request.CatsFilter(
                        limit,
                        currentPage,
                        com.example.domain_models.request.Order.DESC
                    )
                )

                if (newCats.isEmpty()) {
                    allCatsLoaded = true
                }

                val allCats = currentCats + newCats

                modifyState { copy(cats = allCats) }
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO: - сделать алерт об ошибке
            }

            modifyState { copy(isLoading = false) }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val cats: List<com.example.domain_models.response.CatModel> = emptyList()
    )

    sealed interface Actions {

    }

}