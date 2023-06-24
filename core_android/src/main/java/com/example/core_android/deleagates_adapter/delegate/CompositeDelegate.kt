package com.example.core_android.deleagates_adapter.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class CompositeDelegate<Item : CompositeItem, Binding : ViewBinding> {

    fun createHolder(inflater: LayoutInflater, parent: ViewGroup): Holder<Item, Binding> {
        val binding = provideBinding(inflater, parent)
        return Holder(
            binding = binding,
            bindCallback = { bind(it) }
        )
    }

    abstract fun canUseForViewType(item: CompositeItem): Boolean

    abstract fun provideBinding(inflater: LayoutInflater, parent: ViewGroup): Binding

    abstract fun Binding.bind(item: Item)

}

class Holder<Item : CompositeItem, Binding : ViewBinding>(
    private val binding: Binding,
    private val bindCallback: Binding.(item: Item) -> Unit
) : ViewHolder(binding.root) {

    fun onBind(item: Item) {
        binding.bindCallback(item)
    }

}