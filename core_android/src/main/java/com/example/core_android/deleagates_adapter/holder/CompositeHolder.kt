package com.example.core_android.deleagates_adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import kotlin.properties.Delegates.notNull

abstract class CompositeHolder<Item : CompositeItem<*>, Binding : ViewBinding> {

    private var holder: Holder<Item, Binding> by notNull()

    fun canUseForViewType(item: CompositeItem<*>): Boolean = (item as? Item) != null

    fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        val binding = provideBinding(inflater, parent)



        return holder
    }

    abstract fun provideBinding(inflater: LayoutInflater, parent: ViewGroup): Binding

    abstract fun bind(item: Item, binding: Binding)

}

abstract class Holder<Item : CompositeItem<*>, Binding : ViewBinding>(
    private val binding: Binding
): ViewHolder(binding.root) {

    fun bindItem(item: Item) {
        binding.onBind(item)
    }

    abstract fun Binding.onBind(item: Item)

}