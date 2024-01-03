package com.yoox.items.domain.interactors

import com.yoox.items.app.api.response.mapToDomain
import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.domain.model.ItemDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val repository: ItemsRepository
) {

    suspend operator fun invoke(cod: String): Flow<ItemDetails?> = channelFlow {
        repository.getItem(cod).collectLatest { data ->
            data?.let {
                send(it.mapToDomain())
            } ?: send(null)
        }
    }
}
