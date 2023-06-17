package com.example.data.repositories

import com.example.data.mappers.CatsFilterMapper
import com.example.data.mappers.ImagesMapper
import com.example.data_api.repositories.CatsRepository
import com.example.domain_api.models.request.CatsFilter
import com.example.domain_api.models.response.CatModel
import com.example.network.api.ImagesApi
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi
): CatsRepository {

    private val catsFilterMapper = CatsFilterMapper()
    private val imagesMapper = ImagesMapper()

    override suspend fun getCats(filter: CatsFilter): List<CatModel> {
        val filterMap = catsFilterMapper.mapTo(filter)
        val catsImages = imagesApi.getImages(filterMap)
        return imagesMapper.mapTo(catsImages)
    }

}