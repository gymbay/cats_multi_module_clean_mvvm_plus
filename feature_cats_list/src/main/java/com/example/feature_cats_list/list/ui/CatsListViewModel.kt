package com.example.feature_cats_list.list.ui

import androidx.lifecycle.viewModelScope
import com.example.core_android.architecture.BaseViewModel
import com.example.domain_api.models.usecases.CatsUseCase
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.request.Order
import com.example.domain_models.response.CatModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CatsListViewModel @Inject constructor(
    private val catsUseCase: CatsUseCase
) : BaseViewModel<CatsListViewModel.UiState, CatsListViewModel.Actions>(UiState()) {

    private var currentPage: Int = -1
    private var allCatsLoaded: Boolean = false

    private var listLoadingJob: Job? = null

    init {
        nextPage()
    }

    fun nextPage() {
        if (allCatsLoaded) return
        if (listLoadingJob?.isActive == true) return

        listLoadingJob = viewModelScope.launch {
            modifyState { copy(isLoading = true) }

            try {
                val nextPage = currentPage + 1
                val currentCats = getState().cats
                val newCats = catsUseCase.getCats(
                    CatsFilter(
                        LIMIT,
                        nextPage,
                        Order.DESC
                    )
                )

                if (newCats.isEmpty()) {
                    allCatsLoaded = true
                }

                val allCats = currentCats + newCats
                currentPage = nextPage

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
        val cats: List<CatModel> = emptyList()
    )

    sealed interface Actions {

    }

    companion object {

        private const val LIMIT = 50
        const val ITEMS_TO_NEXT_PAGE = 20
        const val SPAN_COUNT = 2

    }

}