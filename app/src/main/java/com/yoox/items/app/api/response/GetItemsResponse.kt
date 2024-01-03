package com.yoox.items.app.api.response

import com.google.gson.annotations.SerializedName
import com.yoox.items.domain.model.Item
import com.yoox.items.utilities.MappableToDomain
import com.yoox.items.utilities.extensions.PaginatedList
import com.yoox.items.utilities.extensions.toPaginatedList

data class GetItemsResponse(
    @SerializedName("Items")
    val items: List<RemoteItem>,
    @SerializedName("Analytics")
    val pagination: RemotePagination
) : MappableToDomain<PaginatedList<Item>> {

    override fun mapToDomain(): PaginatedList<Item> {
        return items.map(RemoteItem::mapToDomain).toPaginatedList(pagination.mapToDomain())
    }
}
