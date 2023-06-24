package com.example.core_android.deleagates_adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.core_android.deleagates_adapter.delegate.CompositeDelegate

class CompositePagingAdapter(
    delegates: List<CompositeDelegate<*, *>>,
    private val onNextPageCallback: () -> Unit,
    private val itemsToNextPage: Int
) : CompositeAdapter(delegates) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(OnPagingScrollListener(
            onNextPageCallback,
            itemsToNextPage
        ))
    }

    internal class OnPagingScrollListener(
        private val onNextPageCallback: () -> Unit,
        private val itemsToNextPage: Int
    ): OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val lastVisibleIndex = layoutManager.findLastVisibleItemPosition()
            val itemCount = layoutManager.itemCount

            val itemsToEnd = itemCount - lastVisibleIndex + 1

            if (itemsToEnd <= itemsToNextPage) {
                onNextPageCallback()
            }
        }

    }

    class Builder: CompositeAdapter.Builder() {

        private var onNextPageCallback: (() -> Unit)? = null
        private var itemsToNextPage: Int = 10

        fun setOnNextPageCallback(callback: () -> Unit): Builder {
            onNextPageCallback = callback
            return this
        }

        fun setItemsToNextPage(count: Int): Builder {
            if (count <= 0) throw IllegalStateException("itemsToNextPage must be >= 0!")
            itemsToNextPage = count
            return this
        }

        override fun checks() {
            super.checks()
            if (onNextPageCallback == null) throw IllegalStateException("NextPageCallback must be set!")
        }

        override fun build(): CompositeAdapter {
            checks()

            return CompositePagingAdapter(
                delegates,
                onNextPageCallback!!,
                itemsToNextPage
            )
        }

    }

}