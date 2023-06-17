package com.example.data_api.repositories

import com.example.domain_api.models.request.CatsFilter
import com.example.domain_api.models.response.CatModel

interface CatsRepository {
    suspend fun getCats(filter: CatsFilter): List<CatModel>
}