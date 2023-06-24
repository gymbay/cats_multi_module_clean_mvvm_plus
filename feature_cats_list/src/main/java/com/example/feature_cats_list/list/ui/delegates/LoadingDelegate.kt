package com.example.feature_cats_list.list.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_android.deleagates_adapter.delegate.CompositeDelegate
import com.example.core_android.deleagates_adapter.delegate.CompositeItem
import com.example.feature_cats_list.databinding.CatsListLoadingItemBinding
import com.example.feature_cats_list.list.ui.models.CatListItem

class LoadingDelegate : CompositeDelegate<CatListItem.Loading, CatsListLoadingItemBinding>() {

    override fun canUseForViewType(item: CompositeItem) = item is CatListItem.Loading

    override fun provideBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): CatsListLoadingItemBinding {
        return CatsListLoadingItemBinding.inflate(inflater, parent, false)
    }

    override fun CatsListLoadingItemBinding.bind(item: CatListItem.Loading) = Unit
}