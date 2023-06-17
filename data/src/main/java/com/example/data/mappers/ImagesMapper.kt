package com.example.data.mappers

import com.example.domain_api.models.response.BreedModel
import com.example.domain_api.models.response.CatModel
import com.example.domain_api.models.response.CategoryModel
import com.example.domain_api.models.response.WeightModel
import com.example.network.models.Breed
import com.example.network.models.Category
import com.example.network.models.Image
import com.example.network.models.Weight

class ImagesMapper {

    fun mapTo(images: List<Image>): List<CatModel> = images.map(::convert)

    private fun convert(image: Image): CatModel {
        return CatModel(
            id = image.id ?: throw error("image.id"),
            url = image.url ?: throw error("image.url"),
            width = image.width ?: throw error("image.width"),
            height = image.height ?: throw error("image.height"),
            breeds = image.breeds?.map(::convert),
            categories = image.categories?.map(::convert)
        )
    }

    private fun convert(breed: Breed): BreedModel {
        return BreedModel(
            id = breed.id ?: throw error("breed.id"),
            weight = convert(breed.weight ?: throw error("breed.id")),
            name = breed.name ?: throw error("breed.name"),
            temperament = breed.temperament ?: throw error("breed.temperament"),
            origin = breed.origin ?: throw error("breed.origin"),
            description = breed.description ?: throw error("breed.description"),
            wikipediaURL = breed.wikipediaURL ?: throw error("breed.wikipediaURL"),
        )
    }

    private fun convert(category: Category): CategoryModel {
        return CategoryModel(
            id = category.id ?: throw error("category.id"),
            name = category.name ?: throw error("category.name")
        )
    }

    private fun convert(weight: Weight): WeightModel {
        return WeightModel(
            imperial = weight.imperial ?: throw error("weight.imperial"),
            metric = weight.metric ?: throw error("weight.metric")
        )
    }

    private fun error(field: String) = IllegalStateException("ImageMapper: $field must not be NULL!")

}