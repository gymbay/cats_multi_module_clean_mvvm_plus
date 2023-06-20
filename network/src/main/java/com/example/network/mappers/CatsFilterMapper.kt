package com.example.network.mappers

import com.example.domain_models.request.CatsFilter

class CatsFilterMapper {

    fun mapTo(catsFilter: com.example.domain_models.request.CatsFilter): Map<String, String> {
        return mutableMapOf(
            "limit" to catsFilter.limit.toString(),
            "page" to catsFilter.page.toString(),
            "order" to catsFilter.order.name
        ).apply {
            catsFilter.hasBreeds?.let {
                val value = if (it) "1" else "0"
                put("has_breeds", value)
            }

            catsFilter.breedIds?.let {
                put("breed_ids", it.joinToString(","))
            }

            catsFilter.categoryIds?.let {
                put("category_ids", it.joinToString(","))
            }

            catsFilter.subId?.let {
                put("sub_id", it)
            }
        }
    }

}