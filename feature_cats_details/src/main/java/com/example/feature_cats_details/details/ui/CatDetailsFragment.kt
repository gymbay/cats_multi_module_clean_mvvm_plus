package com.example.feature_cats_details.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import coil.load
import com.example.core_android.architecture.collectOnStart
import com.example.core_android.architecture.update
import com.example.core_android.delegates.featureStore
import com.example.core_android.delegates.lazyViewModel
import com.example.domain_models.response.CatModel
import com.example.feature_cats_details.R
import com.example.feature_cats_details.common.di.CatDetailsStore
import com.example.feature_cats_details.databinding.FragmentCatDetailsBinding
import com.example.feature_cats_details.details.ui.converters.CatUiConverter
import com.example.feature_dialogs.alert.ui.extensions.showAlert
import com.example.navigation.ARGS
import kotlinx.coroutines.flow.onEach

internal class CatDetailsFragment : Fragment() {

    private val binding: FragmentCatDetailsBinding
        get() = _binding!!
    private var _binding: FragmentCatDetailsBinding? = null

    private val featureStore: CatDetailsStore by featureStore(R.id.featureCatDetails)

    private val viewModel by lazyViewModel {
        val catId = checkNotNull(arguments?.getString(ARGS.CAT_ID))
        featureStore.featureComponent.detailsViewModel.create(catId)
    }

    private val uiConverter by lazy { CatUiConverter(resources) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCatDetailsBinding
        .inflate(inflater)
        .also { _binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.onEach(::handleUIState).collectOnStart(viewLifecycleOwner)
        viewModel.action.onEach(::handleAction).collectOnStart(viewLifecycleOwner)
    }

    private fun handleUIState(state: CatDetailsViewModel.UIState) {
        updateProgress(state.isLoading)
        updateContent(state.catModel)
    }

    private fun updateProgress(isLoading: Boolean) {
        with(binding.progress) {
            if (isLoading) {
                show()
            } else {
                hide()
            }
        }
        binding.content.isVisible = !isLoading
    }

    private fun updateContent(model: CatModel?) {
        if (model == null) return
        val uiModel = uiConverter.convertTo(model)
        with(binding) {
            ivCat.load(uiModel.imageUrl)
            tvName.update(uiModel.name)
            tvOrigin.update(uiModel.origin)
            tvWeight.update(uiModel.weight)
            tvTemperament.update(uiModel.temperament)
            tvLifeSpan.update(uiModel.lifeSpan)
            tvDescription.update(uiModel.description)
        }
    }

    private fun handleAction(action: CatDetailsViewModel.Actions) {
        when (action) {
            is CatDetailsViewModel.Actions.ShowAlert -> showAlert(
                message = action.message
            )
            CatDetailsViewModel.Actions.GoBack -> Unit
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}