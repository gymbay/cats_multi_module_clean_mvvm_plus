package com.example.feature_cats_list.list.ui.converters

import com.example.domain_models.response.CatModel
import com.example.feature_cats_list.list.ui.models.CatListItem

internal class CatsListUiConverter {

    fun convertTo(cats: List<com.example.domain_models.response.CatModel>, isLoading: Boolean): List<CatListItem> {
        return mutableListOf<CatListItem>().apply {
            addAll(cats.map(::convertTo))

            if (isLoading) {
                add(CatListItem.Loading)
            }
        }
    }

    private fun convertTo(cat: com.example.domain_models.response.CatModel): CatListItem =
        CatListItem.Cat(
            cat.id,
            cat.url
        )

}