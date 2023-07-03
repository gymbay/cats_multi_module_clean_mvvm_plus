package com.example.domain_api.usecases

import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel

interface CatsUseCase {

    suspend fun getCats(filter: CatsFilter): List<CatModel>

    suspend fun getCat(catId: String): CatModel

}