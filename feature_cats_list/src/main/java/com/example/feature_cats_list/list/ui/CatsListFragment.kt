package com.example.feature_cats_list.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core_android.architecture.collectOnStart
import com.example.core_android.delegate_adapter.CompositePagingAdapter
import com.example.core_android.delegates.featureStore
import com.example.core_android.delegates.lazyViewModel
import com.example.feature_cats_list.R
import com.example.feature_cats_list.common.di.FeatureCatsListStore
import com.example.feature_cats_list.databinding.CatsListFragmentBinding
import com.example.feature_cats_list.list.ui.converters.CatsListUiConverter
import com.example.feature_cats_list.list.ui.delegates.CatsDelegate
import com.example.feature_cats_list.list.ui.delegates.LoadingDelegate
import com.example.feature_dialogs.alert.ui.extensions.showAlert
import kotlinx.coroutines.flow.onEach
import kotlin.properties.Delegates

internal class CatsListFragment : Fragment() {

    private var binding: CatsListFragmentBinding? = null
    private val store: FeatureCatsListStore by featureStore(R.id.featureCatsList)
    private val viewModel by lazyViewModel {
        store.catsListSubcomponent.viewModel
    }

    private var adapter: CompositePagingAdapter by Delegates.notNull()

    private val uiConverter = CatsListUiConverter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = CatsListFragmentBinding
        .inflate(inflater)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        viewModel.state.onEach(::handleState).collectOnStart(viewLifecycleOwner)
        viewModel.action.onEach(::handleAction).collectOnStart(viewLifecycleOwner)
    }

    private fun initAdapter() {
        adapter = CompositePagingAdapter
            .Builder(viewModel::nextPage)
            .add(CatsDelegate())
            .add(LoadingDelegate())
            .build()

        binding?.rvCats?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvCats?.adapter = adapter
    }

    private fun handleState(state: CatsListViewModel.UiState) {
        val convertedState = uiConverter.convertTo(state.cats, state.isLoading)
        adapter.submitList(convertedState)
    }

    private fun handleAction(action: CatsListViewModel.Actions) {
        when (action) {
            is CatsListViewModel.Actions.ShowAlert -> showAlert(action.alert)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}