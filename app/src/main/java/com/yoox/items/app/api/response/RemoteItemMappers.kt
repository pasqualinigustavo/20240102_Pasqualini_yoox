package com.yoox.items.app.api.response

import com.yoox.items.domain.model.Item

fun RemoteItem.mapToDomain() = Item(
    cod = cod,
    brand = brand,
    microCategory = microCategory,
    fullPrice = fullPrice,
    discountedPrice = discountedPrice,
    formattedFullPrice = formattedFullPrice,
    formattedDiscountedPrice = formattedDiscountedPrice,
    urlImage = "https://www.yoox.com/images/items/" + cod.substring(0, 2) + "/" + cod + "_13_d.jpg",
    itemDescription = itemDescription?.mapToDomain(),
)

private fun RemoteItem.ItemDescription.mapToDomain() =
    Item.Details.ItemDescription(
        productInfo = productInfo,
    )
