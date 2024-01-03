package com.yoox.items.app.repository

import com.yoox.items.app.api.Api
import com.yoox.items.app.api.ApiResponseHandler
import com.yoox.items.app.api.response.GetItemsResponse
import com.yoox.items.app.api.response.RemoteItemDetails
import com.yoox.items.app.api.response.mapToDomain
import com.yoox.items.domain.model.Item
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.utilities.extensions.PaginatedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val api: Api,
    val apiResponseHandler: ApiResponseHandler<*>
) : ItemsRepository {

    override suspend fun getItems(page: String): Flow<GetItemsResponse?> {
        return apiResponseHandler.handleApiCall {
            api.getItems("$page.json")
        }
    }

    override suspend fun getItem(cod: String): Flow<RemoteItemDetails?> {
        return apiResponseHandler.handleApiCall {
            api.getItem("$cod.json")
        }
    }
}

