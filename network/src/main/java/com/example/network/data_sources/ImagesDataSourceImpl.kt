package com.example.network.data_sources

import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel
import com.example.network.api.ImagesApi
import com.example.network.mappers.CatsFilterMapper
import com.example.network.mappers.ImagesMapper
import com.example.network_api.data_sources.ImagesDataSource
import javax.inject.Inject

class ImagesDataSourceImpl @Inject constructor(
    private val imagesApi: ImagesApi
): ImagesDataSource {

    private val catsFilterMapper = CatsFilterMapper()
    private val imagesMapper = ImagesMapper()

    override suspend fun getImages(filter: CatsFilter): List<CatModel> {
        val filterMap = catsFilterMapper.mapTo(filter)
        val catsImages = imagesApi.getImages(filterMap)
        return imagesMapper.mapTo(catsImages)
    }

}