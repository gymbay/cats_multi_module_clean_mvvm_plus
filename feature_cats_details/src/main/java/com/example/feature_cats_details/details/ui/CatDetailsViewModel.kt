package com.example.feature_cats_details.details.ui

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import com.example.core_android.architecture.BaseViewModel
import com.example.domain_api.usecases.CatsUseCase
import com.example.domain_models.response.CatModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

internal class CatDetailsViewModel @AssistedInject constructor(
    @Assisted private val catId: String,
    private val catsUseCase: CatsUseCase,
    private val resources: Resources
) : BaseViewModel<CatDetailsViewModel.UIState, CatDetailsViewModel.Actions>(
    UIState()
) {

    init {
        startCatLoading(catId)
    }

    private fun startCatLoading(catId: String) = viewModelScope.launch {
        modifyState { copy(isLoading = true) }

        try {
            val cat = catsUseCase.getCat(catId)
            modifyState { copy(catModel = cat) }
        } catch (e: Exception) {
            e.printStackTrace()
            val error = resources.getString(com.example.core_android.R.string.service_not_available)
            onAction(Actions.ShowAlert(error))
        }

        modifyState { copy(isLoading = false) }
    }

    data class UIState(
        val catModel: CatModel? = null,
        val isLoading: Boolean = false
    )

    sealed interface Actions {
        object GoBack : Actions
        data class ShowAlert(val message: String) : Actions
    }

    @AssistedFactory
    interface Factory {
        fun create(catId: String): CatDetailsViewModel
    }

}