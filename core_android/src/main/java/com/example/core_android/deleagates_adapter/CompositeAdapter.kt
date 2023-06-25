package com.example.core_android.deleagates_adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core_android.deleagates_adapter.delegate.CompositeDelegate
import com.example.core_android.deleagates_adapter.delegate.CompositeItem
import com.example.core_android.deleagates_adapter.delegate.Holder

open class CompositeAdapter(
    private val delegates: List<CompositeDelegate<*, *>>
) : ListAdapter<CompositeItem, ViewHolder>(CompositeDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        val viewType = delegates.indexOfFirst { it.canUseForViewType(item) }
        if (viewType == NOT_FOUND) {
            throw IllegalStateException("Not found delegate for type ${item::class.java.name}")
        }
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val delegate = delegates[viewType]
        return delegate.createHolder(LayoutInflater.from(parent.context), parent)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as Holder<CompositeItem, *>
        holder.onBind(currentList[position])
    }

    class CompositeDiffUtil : DiffUtil.ItemCallback<CompositeItem>() {

        override fun areItemsTheSame(oldItem: CompositeItem, newItem: CompositeItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CompositeItem, newItem: CompositeItem): Boolean {
            return oldItem == newItem
        }

    }

    open class Builder {

        private var delegates: MutableList<CompositeDelegate<*, *>> = mutableListOf()

        fun add(delegate: CompositeDelegate<*, *>): Builder {
            delegates.add(delegate)
            return this
        }

        open fun build(): CompositeAdapter {
            if (delegates.isEmpty()) throw IllegalStateException("Add at least one delegate!")

            return CompositeAdapter(delegates)
        }

    }

    companion object {

        private const val NOT_FOUND = -1

    }

}