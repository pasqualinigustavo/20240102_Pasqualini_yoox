package com.yoox.items.presentation.history

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.yoox.items.MainCoroutineRule
import com.yoox.items.app.api.ApiResponseHandler
import com.yoox.items.app.api.response.GetItemsResponse
import com.yoox.items.app.repository.HistoryRepository
import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.domain.interactors.GetHistoryUseCase
import com.yoox.items.domain.interactors.GetItemsUseCase
import com.yoox.items.presentation.history.data.getHistoryDataSample
import com.yoox.items.presentation.history.data.historyItem
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
class HistoryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()
    lateinit var viewModel: HistoryViewModel

    private lateinit var historyRepository: HistoryRepository
    private val getHistoryUseCase: GetHistoryUseCase = mock()

    @Before
    fun init() {
        historyRepository = mockk()
        viewModel = getHistoryViewModel()
    }

    private fun getHistoryViewModel(): HistoryViewModel {
        return HistoryViewModel(
            getHistoryUseCase = getHistoryUseCase
        )
    }

    @Test
    fun testHistoryItems() {
        runBlocking {
            // Given
            coEvery { historyRepository.addItem(historyItem) } returns flowOf(
                true
            )
            coEvery { historyRepository.getHistory() } returns flowOf(
                getHistoryDataSample.toMutableList()
            )

            // When
            val item = historyRepository.getHistory().first()

            // Check
            Truth.assertThat(item).isNotNull()
        }
    }

}
