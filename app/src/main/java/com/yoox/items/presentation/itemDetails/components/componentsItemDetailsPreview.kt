package com.yoox.items.presentation.itemDetails.components

import com.yoox.items.domain.model.ItemDetails

val itemDetailsPreview = ItemDetails(
    cod = "",
    brand = ItemDetails.Details.Brand("Brand"),
    category = ItemDetails.Details.Category("Category"),
    price = ItemDetails.Details.Price(1.0, 2.0),
    formattedPrice = ItemDetails.Details.FormattedPrice("", ""),
    urlImage = "https://fastly.picsum.photos/id/1081/200/300.jpg",
    itemDescription = null,
    colors = listOf(
        ItemDetails.Details.Color("gold", "test"),
        ItemDetails.Details.Color("silver", "test")
    ),
    sizes = listOf(
        ItemDetails.Details.Size("SM", "test"),
        ItemDetails.Details.Size("XL", "test")
    ),
)
