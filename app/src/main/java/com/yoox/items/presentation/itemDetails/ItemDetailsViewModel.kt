package com.yoox.items.presentation.itemDetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yoox.items.BaseViewModel
import com.yoox.items.domain.interactors.GetItemUseCase
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.utilities.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    val getItemUseCase: GetItemUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    val cod: String = checkNotNull(savedStateHandle["cod"])

    private val lock = Any()
    private var loadJob: Job? = null

    var itemsState by mutableStateOf<ItemDetails?>(null)
        private set

    var isLoadingState by mutableStateOf(LoadingState.NONE)
        private set

    override fun onAttached() {
        super.onAttached()
        viewModelScope.launch {
            loadItem()
        }
    }

    private fun updateLoadingState(loadingState: LoadingState) {
        synchronized(lock) {
            isLoadingState = loadingState
        }
    }

    private fun loadItem() {
        updateLoadingState(LoadingState.LOADING)
        loadJob?.cancel()
        loadJob = viewModelScope.launch(Const.requestDispatchers) {
            getItemUseCase.invoke(cod).catch {
                updateLoadingState(LoadingState.ERROR)
            }.collectLatest { result ->
                result?.let {
                    itemsState = result
                    updateLoadingState(LoadingState.READY)
                }
            }
        }
    }
}
