package com.example.feature_cats_details.details.ui

import com.example.core_android.architecture.BaseViewModel
import com.example.core_android.providers.StringProvider
import com.example.domain_api.usecases.CatsUseCase
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel
import com.example.test_utils.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val inputCatId = "gVrhv_yAY"
    private val catsUseCase = object : CatsUseCase {

        override suspend fun getCats(filter: CatsFilter): List<CatModel> {
            throw IllegalStateException()
        }

        override suspend fun getCat(catId: String): CatModel {
            return if (catId == inputCatId) {
                CatModel(
                    inputCatId,
                    "https://cdn2.thecatapi.com/images/gVrhv_yAY43.jpg",
                    1600,
                    1051
                )
            } else {
                throw IllegalStateException()
            }
        }

    }

    private val expectedErrorMessage = "Сервис временно недоступен, попробуйте позже"
    private val stringProvider = object : StringProvider {
        override fun getString(resId: Int, args: Array<Any>): String {
            return when (resId) {
                com.example.core_android.R.string.service_not_available ->
                    expectedErrorMessage

                else -> throw NotImplementedError()
            }
        }
    }

    @Before
    fun setUp() {
        BaseViewModel.turnOnTestMode()
    }

    @Test
    fun test_success_cat_loading() = runTest {
        val viewModel = CatDetailsViewModel(
            inputCatId,
            catsUseCase,
            stringProvider
        )

        val stateLog = viewModel.getStateLog()

        assertTrue(stateLog[0].isLoading)
        assertEquals(inputCatId, stateLog[1].catModel?.id)
        assertFalse(stateLog[2].isLoading)
    }

    @Test
    fun test_failure_cat_loading() = runTest {
        val viewModel = CatDetailsViewModel(
            "4433",
            catsUseCase,
            stringProvider
        )

        val action = viewModel.action.first()

        val alert = action as? CatDetailsViewModel.Actions.ShowAlert
        assertNotNull(alert)
        assertEquals(expectedErrorMessage, alert?.message)
    }

}