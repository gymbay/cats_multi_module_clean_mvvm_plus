package com.example.feature_cats_list.list.ui.models

sealed class CatListItem(open val id: String) {

    data class Cat(
        override val id: String,
        val url: String
    ): CatListItem(id)

    object Loading: CatListItem("Loading")

}