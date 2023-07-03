package com.example.data_api.repositories

import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel

interface CatsRepository {

    suspend fun getCats(filter: CatsFilter): List<CatModel>

    suspend fun getCat(catId: String): CatModel

}