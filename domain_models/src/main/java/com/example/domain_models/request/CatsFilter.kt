package com.example.domain_models.request

data class CatsFilter(
    val limit: Int,
    val page: Int,
    val order: Order,
    val hasBreeds: Boolean? = null,
    val breedIds: List<String>? = null,
    val categoryIds: List<Long>? = null,
    val subId: String? = null,
)

enum class Order {
    ASC, DESC, RAND
}
