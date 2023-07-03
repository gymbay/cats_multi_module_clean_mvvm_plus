package com.example.data.repositories

import com.example.data_api.repositories.CatsRepository
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel
import com.example.network_api.data_sources.ImagesDataSource
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val imagesDataSource: ImagesDataSource
): CatsRepository {

    override suspend fun getCats(filter: CatsFilter): List<CatModel> {
        return imagesDataSource.getImages(filter)
    }

    override suspend fun getCat(catId: String): CatModel {
        return imagesDataSource.getImage(catId)
    }

}