package com.example.network.models

data class Image(
    val breeds: List<Breed>? = null,
    val categories: List<Category>? = null,
    val id: String? = null,
    val url: String? = null,
    val width: Long? = null,
    val height: Long? = null
)