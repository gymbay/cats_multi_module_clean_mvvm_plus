package com.example.domain_models.response

data class BreedModel(
    val id: String,
    val weight: WeightModel,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val wikipediaURL: String?,
    val lifeSpan: String?,
)
