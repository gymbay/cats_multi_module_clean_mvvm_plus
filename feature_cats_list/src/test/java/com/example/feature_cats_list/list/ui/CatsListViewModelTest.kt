package com.example.feature_cats_list.list.ui

import com.example.core_android.architecture.BaseViewModel
import com.example.core_android.providers.StringProvider
import com.example.domain_api.usecases.CatsUseCase
import com.example.domain_models.request.CatsFilter
import com.example.domain_models.response.CatModel
import com.example.test_utils.MainDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class CatsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val expectedFirstCatId = "gVrhv_yAY"
    private val expectedSecondCatId = "gVrhv_yAY43"
    private val catsUseCase = object : CatsUseCase {

        override suspend fun getCats(filter: CatsFilter): List<CatModel> {
            if (filter.limit == 1 && filter.page == 0) {
                return listOf(
                    CatModel(
                        expectedFirstCatId,
                        "https://cdn2.thecatapi.com/images/gVrhv_yAY.jpg",
                        1600,
                        1051
                    )
                )
            } else if (filter.limit == 1 && filter.page == 1) {
                return listOf(
                    CatModel(
                        expectedSecondCatId,
                        "https://cdn2.thecatapi.com/images/gVrhv_yAY43.jpg",
                        1600,
                        1051
                    )
                )
            } else {
                throw IllegalStateException()
            }
        }

        override suspend fun getCat(catId: String): CatModel {
            throw IllegalStateException()
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

    private val viewModel = CatsListViewModel(catsUseCase, stringProvider)

    @Before
    fun setUp() {
        BaseViewModel.turnOnTestMode()
    }

    @Test
    fun test_success_loading_first_page() = runTest {
        viewModel.nextPage(0, 1)

        val stateLog = viewModel.getStateLog()

        assertTrue(stateLog[0].isLoading)
        assertEquals(expectedFirstCatId, stateLog[1].cats.firstOrNull()?.id)
        assertFalse(stateLog[2].isLoading)
    }

    @Test
    fun test_success_loading_second_page() = runTest {
        viewModel.nextPage(0, 1)
        viewModel.clearStateLog()
        viewModel.nextPage(1, 1)

        val stateLog = viewModel.getStateLog()

        assertTrue(stateLog[0].isLoading)
        assertEquals(2, stateLog[1].cats.size)
        assertEquals(expectedSecondCatId, stateLog[1].cats.lastOrNull()?.id)
        assertFalse(stateLog[2].isLoading)
    }

    @Test
    fun test_failure_loading_page() = runTest {
        viewModel.nextPage(3, 1)
        val action = viewModel.action.first()

        assertTrue(action is CatsListViewModel.Actions.ShowAlert)
        assertEquals(expectedErrorMessage, (action as CatsListViewModel.Actions.ShowAlert).message)
    }

    @Test
    fun test_on_cat_click() = runTest {
        val expectedCatId = "123"
        viewModel.onCatClick(expectedCatId)
        val action = viewModel.action.first()

        assertTrue(action is CatsListViewModel.Actions.ToCatDetails)
        assertEquals(expectedCatId, (action as CatsListViewModel.Actions.ToCatDetails).catId)
    }

}