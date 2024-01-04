package com.yoox.items.presentation.items.data

import com.yoox.items.app.api.response.GetItemsResponse
import com.yoox.items.app.api.response.RemoteItem
import com.yoox.items.app.api.response.RemotePagination

private val item = RemoteItem(
    cod = "Cod10",
    brand = "brand",
    microCategory = "category",
    fullPrice = 1.0,
    discountedPrice = 1.0,
    formattedFullPrice = "$ 1.0",
    formattedDiscountedPrice = "$ 1.0",
    itemDescription = null,
    colors = null,
    sizes = null,
)

private val remotePagination = RemotePagination(
    currentPage = 1,
    pageSize = 40,
    totalPages = 2
)

val getItemsResponseDataSample = GetItemsResponse(
    items = listOf(item),
    pagination = remotePagination
)