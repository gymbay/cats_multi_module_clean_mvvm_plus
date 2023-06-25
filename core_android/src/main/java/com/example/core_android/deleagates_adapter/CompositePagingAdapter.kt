package com.example.core_android.deleagates_adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.core_android.deleagates_adapter.delegate.CompositeDelegate

typealias PageRequest = (page: Int, pageSize: Int) -> Unit

class CompositePagingAdapter(
    delegates: List<CompositeDelegate<*, *>>,
    private val pageSize: Int = DEFAULT_PAGE_SIZE,
    private val preloadCount: Int = pageSize / 2,
    private val nextPageRequest: PageRequest
) : CompositeAdapter(delegates) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleIndex = layoutManager.findLastVisibleItemPosition()
                val itemCount = layoutManager.itemCount

                nextPageHandler(lastVisibleIndex, itemCount)
            }

        })

        nextPageRequest(0, pageSize)
    }

    private fun nextPageHandler(lastVisibleIndex: Int, itemsCount: Int) {
        val lastItems = itemsCount - lastVisibleIndex + 1
        if (lastItems <= preloadCount) {
            val nextPage = (itemsCount / pageSize) + 1
            nextPageRequest(nextPage, pageSize)
        }
    }

    class Builder(
        private val nextPageRequest: PageRequest
    ) {

        private var delegates: MutableList<CompositeDelegate<*, *>> = mutableListOf()
        private var pageSize: Int = DEFAULT_PAGE_SIZE
        private var preloadCount: Int = pageSize / 2

        fun add(delegate: CompositeDelegate<*, *>): Builder {
            delegates.add(delegate)
            return this
        }

        fun setPageSize(page: Int): Builder {
            pageSize = page
            return this
        }

        fun setPreloadCount(count: Int): Builder {
            preloadCount = count
            return this
        }

        fun build(): CompositeAdapter {
            if (delegates.isEmpty()) throw IllegalStateException("Add at least one delegate!")

            return CompositePagingAdapter(
                delegates,
                pageSize,
                preloadCount,
                nextPageRequest
            )
        }

    }

    companion object {

        private const val DEFAULT_PAGE_SIZE = 50

    }

}