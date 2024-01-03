package com.yoox.items.app.api.response

import com.yoox.items.domain.model.Item

fun RemoteItem.mapToDomain() = Item(
    cod = cod,
    brand = brand,
    microCategory = microCategory,
    formattedFullPrice = formattedFullPrice,
    formattedDiscountedPrice = formattedDiscountedPrice,
    urlImage = "https://www.yoox.com/images/items/" + cod.substring(0, 2) + "/" + cod + "_13_d.jpg",
    itemDescription = itemDescription?.mapToDomain(),
    colors = mapColors(),
    sizes = mapSizes()
)

private fun RemoteItem.ItemDescription.mapToDomain() =
    Item.Details.ItemDescription(
        productInfo = productInfo,
    )

private fun RemoteItem.Color.mapToDomain() =
    Item.Details.Color(
        name = name,
        description = description,
    )

private fun RemoteItem.mapColors() =
    (colors ?: emptyList())
        .map { color ->
            color.mapToDomain()
        }

private fun RemoteItem.Size.mapToDomain() =
    Item.Details.Size(
        name = name,
        text = text,
    )

private fun RemoteItem.mapSizes() =
    (sizes ?: emptyList())
        .map { size ->
            size.mapToDomain()
        }
