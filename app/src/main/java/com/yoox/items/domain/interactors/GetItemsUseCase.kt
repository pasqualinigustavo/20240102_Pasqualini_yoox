package com.yoox.items.domain.interactors

import com.yoox.items.app.repository.ItemsRepository
import com.yoox.items.domain.model.Item
import com.yoox.items.utilities.annotations.OpenForTesting
import com.yoox.items.utilities.extensions.PaginatedList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@OpenForTesting
open class GetItemsUseCase @Inject constructor(
    private val repository: ItemsRepository
) {

    fun invoke(page: String): Flow<PaginatedList<Item>?> = channelFlow {
        repository.getItems(page).collectLatest { data ->
            data?.let {
                send(it.mapToDomain())
            } ?: send(null)
        }
    }
}
