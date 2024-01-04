package com.yoox.items.presentation.itemDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.yoox.items.MainCoroutineRule
import com.yoox.items.app.api.ApiResponseHandler
import com.yoox.items.app.api.response.RemoteItemDetails
import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.domain.interactors.GetItemUseCase
import com.yoox.items.presentation.itemDetails.data.itemDetailsSampleData
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
class ItemDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()
    lateinit var viewModel: ItemDetailsViewModel

    private lateinit var itemsRepository: ItemsRepository
    private val apiResponseHandler: ApiResponseHandler<*> = mockk(relaxed = true)
    private val getItemUseCase: GetItemUseCase = mock()
    private val savedStateHandle: SavedStateHandle = mock()

    @Before
    fun init() {
        itemsRepository = mockk()
        viewModel = getItemsViewModel()
    }

    private fun getItemsViewModel(): ItemDetailsViewModel {
        return ItemDetailsViewModel(
            getItemUseCase = getItemUseCase,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun testsGetItem() {
        runBlocking {
            // Given
            coEvery { apiResponseHandler.handleApiCall<RemoteItemDetails>(any()) } returns flowOf(
                itemDetailsSampleData
            )
            coEvery { itemsRepository.getItem("cod10") } returns flowOf(
                itemDetailsSampleData
            )

            // When
            val item = itemsRepository.getItem("cod10").first()

            // Check
            Truth.assertThat(item).isNotNull()
        }
    }

}
