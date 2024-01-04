package com.yoox.items.presentation.itemDetails.data

import com.yoox.items.app.api.response.RemoteItemDetails

val itemDetailsSampleData = RemoteItemDetails(
    cod = "Cod10",
    brand = RemoteItemDetails.Brand("brand"),
    category = RemoteItemDetails.Category("category"),
    price = RemoteItemDetails.Price(1.0, 1.0),
    formattedPrice = RemoteItemDetails.FormattedPrice("1.0", "1.0"),
    itemDescription = null,
    colors = null,
    sizes = null,
)