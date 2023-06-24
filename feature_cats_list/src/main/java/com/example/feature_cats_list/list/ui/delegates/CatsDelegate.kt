package com.example.feature_cats_list.list.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.core_android.deleagates_adapter.delegate.CompositeDelegate
import com.example.core_android.deleagates_adapter.delegate.CompositeItem
import com.example.feature_cats_list.databinding.CatsListItemBinding
import com.example.feature_cats_list.list.ui.models.CatListItem

class CatsDelegate : CompositeDelegate<CatListItem.Cat, CatsListItemBinding>() {

    override fun canUseForViewType(item: CompositeItem) = item is CatListItem.Cat

    override fun provideBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = CatsListItemBinding.inflate(inflater, parent, false)

    override fun CatsListItemBinding.bind(item: CatListItem.Cat) {
        ivCat.load(item.url)
    }

}