package com.example.network_api.data_sources

import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel

interface ImagesDataSource {

    suspend fun getImages(filter: CatsFilter): List<CatModel>

}