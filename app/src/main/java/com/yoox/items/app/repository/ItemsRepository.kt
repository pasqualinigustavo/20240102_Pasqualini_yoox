package com.yoox.items.app.repository

import com.yoox.items.app.api.response.GetItemsResponse
import com.yoox.items.app.api.response.RemoteItemDetails
import com.yoox.items.domain.model.Item
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.utilities.extensions.PaginatedList
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getItem(cod: String): Flow<RemoteItemDetails?>

    suspend fun getItems(page: String): Flow<GetItemsResponse?>
}
