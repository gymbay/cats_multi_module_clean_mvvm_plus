package com.example.feature_cats_list.list.ui

import androidx.lifecycle.viewModelScope
import com.example.core_android.architecture.BaseViewModel
import com.example.domain_api.usecases.CatsUseCase
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.request.Order
import com.example.domain_models.response.CatModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CatsListViewModel @Inject constructor(
    private val catsUseCase: CatsUseCase
) : BaseViewModel<CatsListViewModel.UiState, CatsListViewModel.Actions>(UiState()) {

    private var allCatsLoaded: Boolean = false
    private var loadingJob: Job? = null

    fun nextPage(page: Int, pageSize: Int) {
        if (allCatsLoaded) return
        if (loadingJob?.isActive == true) return

        loadingJob = viewModelScope.launch {
            modifyState { copy(isLoading = true) }

            try {
                val currentCats = getState().cats
                val newCats = catsUseCase.getCats(
                    CatsFilter(
                        pageSize,
                        page,
                        Order.RAND
                    )
                )

                if (newCats.size < pageSize) {
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
        val cats: List<CatModel> = emptyList()
    )

    sealed interface Actions {

    }

}