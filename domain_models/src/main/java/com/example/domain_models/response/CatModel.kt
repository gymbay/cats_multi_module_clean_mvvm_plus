package com.example.domain_models.response

data class CatModel(
    val id: String,
    val url: String,
    val width: Long,
    val height: Long,
    val breeds: List<BreedModel>? = null,
    val categories: List<CategoryModel>? = null
)