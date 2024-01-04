package com.yoox.items.presentation.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.yoox.items.BaseViewModel
import com.yoox.items.domain.interactors.GetHistoryUseCase
import com.yoox.items.domain.interactors.GetItemsUseCase
import com.yoox.items.domain.model.History
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.utilities.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    val getHistoryUseCase: GetHistoryUseCase,
) : BaseViewModel() {

    private val lock = Any()
    private var loadJob: Job? = null
    var isRefreshingState by mutableStateOf<Boolean?>(null)
        private set

    var itemsState by mutableStateOf(mutableListOf<History>())
        private set

    var isLoadingState by mutableStateOf(LoadingState.NONE)
        private set

    override fun onAttached() {
        super.onAttached()
        viewModelScope.launch {
            loadHistory()
        }
    }

    private fun updateLoadingState(loadingState: LoadingState) {
        synchronized(lock) {
            isLoadingState = loadingState
        }
    }

    private fun loadHistory() {
        updateLoadingState(LoadingState.LOADING)
        loadJob?.cancel()
        loadJob = viewModelScope.launch(Const.requestDispatchers) {
            getHistoryUseCase.invoke().catch {
                updateLoadingState(LoadingState.ERROR)
                updateIsRefreshingState(false)
            }.collectLatest { result ->
                result?.let {
                    itemsState.addAll(result)
                    updateIsRefreshingState(false)
                    updateLoadingState(LoadingState.READY)
                }
            }
        }
    }

    fun updateIsRefreshingState(isRefreshing: Boolean) {
        isRefreshingState = isRefreshing
    }
}
