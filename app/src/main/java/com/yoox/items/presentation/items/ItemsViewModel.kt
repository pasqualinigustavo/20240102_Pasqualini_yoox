package com.yoox.items.presentation.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yoox.items.BaseViewModel
import com.yoox.items.domain.interactors.GetItemsUseCase
import com.yoox.items.domain.model.Item
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.utilities.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    val getItemsUseCase: GetItemsUseCase,
) : BaseViewModel() {

    private val lock = Any()
    private var loadJob: Job? = null

    private var nextPage = 1
    private var totalPages = Int.MAX_VALUE
    val items = mutableListOf<Item>()
    var isRefreshingState by mutableStateOf<Boolean?>(null)
        private set

    var itemsState by mutableStateOf(mutableListOf<Item>())
        private set

    var isLoadingState by mutableStateOf(LoadingState.NONE)
        private set

    override fun onAttached() {
        super.onAttached()
        viewModelScope.launch {
            loadFirstPage()
        }
    }

    private fun updateLoadingState(loadingState: LoadingState) {
        synchronized(lock) {
            isLoadingState = loadingState
        }
    }

    fun loadNextPage() {
        updateLoadingState(LoadingState.LOADING)
        loadJob?.cancel()
        loadJob = viewModelScope.launch(Const.requestDispatchers) {
            getItemsUseCase.invoke(nextPage.toString()).catch {
                updateLoadingState(LoadingState.ERROR)
                updateIsRefreshingState(false)
            }.collectLatest { result ->
                result?.let {
                    itemsState.addAll(result)
                    items.addAll(result)
                    nextPage = result.pagination.currentPage + 1
                    totalPages = result.pagination.totalPages
                    updateIsRefreshingState(false)
                    updateLoadingState(LoadingState.READY)
                }
            }
        }
    }

    fun updateIsRefreshingState(isRefreshing: Boolean) {
        isRefreshingState = isRefreshing
    }

    fun onBottomReached() {
        if (loadJob?.isActive == true || isLoadingState == LoadingState.LOADING || nextPage > totalPages) {
            return
        }
        loadJob = viewModelScope.launch {
            loadNextPage()
        }
    }

    private suspend fun loadFirstPage() {
        nextPage = 1
        totalPages = Int.MAX_VALUE
        items.clear()
        itemsState.clear()
        loadNextPage()
    }

    fun onItemTapped(item: Item) {
        navigate(ItemsFragmentDirections.toItemDetailFragment(item.cod))
    }
}
