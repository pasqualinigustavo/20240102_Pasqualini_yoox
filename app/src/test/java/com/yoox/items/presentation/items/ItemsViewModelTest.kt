package com.yoox.items.presentation.items

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.yoox.items.MainCoroutineRule
import com.yoox.items.app.api.ApiResponseHandler
import com.yoox.items.app.api.response.GetItemsResponse
import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.domain.interactors.GetItemsUseCase
import com.yoox.items.presentation.items.data.getItemsResponseDataSample
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()
    lateinit var viewModel: ItemsViewModel

    private lateinit var itemsRepository: ItemsRepository
    private val apiResponseHandler: ApiResponseHandler<*> = mockk(relaxed = true)
    private val getItemsUseCase: GetItemsUseCase = mock()

    @Before
    fun init() {
        itemsRepository = mockk()
        viewModel = getItemsViewModel()
    }

    private fun getItemsViewModel(): ItemsViewModel {
        return ItemsViewModel(
            getItemsUseCase = getItemsUseCase
        )
    }

    @Test
    fun testsGetItems() {
        runBlocking {
            // Given
            coEvery { apiResponseHandler.handleApiCall<GetItemsResponse>(any()) } returns flowOf(
                getItemsResponseDataSample
            )
            coEvery { itemsRepository.getItems("1") } returns flowOf(
                getItemsResponseDataSample
            )

            // When
            val item = itemsRepository.getItems("1").first()

            // Check
            Truth.assertThat(item).isNotNull()
        }
    }

}
