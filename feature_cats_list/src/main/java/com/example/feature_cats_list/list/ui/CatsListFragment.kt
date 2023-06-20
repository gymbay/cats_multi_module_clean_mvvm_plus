package com.example.feature_cats_list.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core_android.delegates.featureStore
import com.example.core_android.delegates.lazyViewModel
import com.example.feature_cats_list.R
import com.example.feature_cats_list.common.di.FeatureCatsListStore
import com.example.feature_cats_list.databinding.CatsListFragmentBinding

internal class CatsListFragment : Fragment() {

    private var binding: CatsListFragmentBinding? = null
    private val store: FeatureCatsListStore by featureStore(R.id.featureCatsList)
    private val viewModel by lazyViewModel {
        store.catsListSubcomponent.viewModel
    }

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


    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}