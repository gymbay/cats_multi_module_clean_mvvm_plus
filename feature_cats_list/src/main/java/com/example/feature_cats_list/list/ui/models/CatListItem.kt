package com.example.feature_cats_list.list.ui.models

import com.example.core_android.deleagates_adapter.holder.CompositeItem

sealed class CatListItem(override val id: String): CompositeItem<String>() {

    data class Cat(
        override val id: String,
        val url: String
    ): CatListItem(id)

    object Loading: CatListItem("Loading")

}