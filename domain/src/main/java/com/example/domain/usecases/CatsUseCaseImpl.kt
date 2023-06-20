package com.example.domain.usecases

import com.example.data_api.repositories.CatsRepository
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel
import com.example.domain_api.models.usecases.CatsUseCase
import javax.inject.Inject

class CatsUseCaseImpl @Inject constructor(
    private val catsRepository: CatsRepository
): CatsUseCase {

    override suspend fun getCats(filter: CatsFilter): List<CatModel> {
        return catsRepository.getCats(filter)
    }

}