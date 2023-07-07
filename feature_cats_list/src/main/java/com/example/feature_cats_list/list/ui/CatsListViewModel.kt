package com.example.feature_cats_list.list.ui

import androidx.lifecycle.viewModelScope
import com.example.core_android.architecture.BaseViewModel
import com.example.core_android.providers.StringProvider
import com.example.domain_api.usecases.CatsUseCase
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.request.Order
import com.example.domain_models.response.CatModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CatsListViewModel @Inject constructor(
    private val catsUseCase: CatsUseCase,
    private val stringProvider: StringProvider
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
                        limit = pageSize,
                        page = page,
                        order = Order.RAND,
                        hasBreeds = true,
                    )
                )

                if (newCats.size < pageSize) {
                    allCatsLoaded = true
                }

                val allCats = currentCats + newCats

                modifyState { copy(cats = allCats) }
            } catch (e: Exception) {
                e.printStackTrace()

                onAction(
                    Actions.ShowAlert(
                        stringProvider.getString(com.example.core_android.R.string.service_not_available)
                    )
                )
            }

            modifyState { copy(isLoading = false) }
        }
    }

    fun onCatClick(catId: String) {
        onAction(Actions.ToCatDetails(catId))
    }

    data class UiState(
        val isLoading: Boolean = false,
        val cats: List<CatModel> = emptyList()
    )

    sealed interface Actions {
        data class ShowAlert(val message: String) : Actions
        data class ToCatDetails(val catId: String) : Actions
    }

}