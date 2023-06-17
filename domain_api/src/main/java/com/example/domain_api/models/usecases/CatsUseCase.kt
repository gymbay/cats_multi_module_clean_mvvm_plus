package com.example.domain_api.models.usecases

import com.example.domain_api.models.request.CatsFilter
import com.example.domain_api.models.response.CatModel

interface CatsUseCase {
    suspend fun getCats(filter: CatsFilter): List<CatModel>
}