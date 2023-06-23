package com.example.core_android.deleagates_adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.core_android.deleagates_adapter.holder.CompositeHolder
import com.example.core_android.deleagates_adapter.holder.CompositeItem

class CompositeAdapter<Item : CompositeItem<*>>(
    private val holders: List<CompositeHolder<Item, *>>
) : ListAdapter<CompositeItem<*>, ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        val item = currentList[position]
        return holders.indexOfFirst { it.canUseForViewType(item) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return holders[viewType].createViewHolder(
            inflater = LayoutInflater.from(parent.context),
            parent = parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    companion object {

        private val diffUtil = object : DiffUtil.ItemCallback<CompositeItem<*>>() {

            override fun areItemsTheSame(
                oldItem: CompositeItem<*>,
                newItem: CompositeItem<*>
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CompositeItem<*>,
                newItem: CompositeItem<*>
            ): Boolean {
                return oldItem == newItem
            }

        }

    }

}