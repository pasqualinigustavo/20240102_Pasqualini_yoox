package com.yoox.items.app.api.response

import com.yoox.items.domain.model.ItemDetails

fun RemoteItemDetails.mapToDomain() = ItemDetails(
    cod = cod,
    brand = brand.mapToDomain(),
    category = category.mapToDomain(),
    price = price.mapToDomain(),
    urlImage = "https://www.yoox.com/images/items/" + cod.substring(0, 2) + "/" + cod + "_13_d.jpg",
    itemDescription = itemDescription?.mapToDomain(),
    colors = mapColors(),
    sizes = mapSizes()
)

private fun RemoteItemDetails.Price.mapToDomain() =
    ItemDetails.Details.Price(
        fullPrice = fullPrice,
        discountedPrice = discountedPrice,
    )

private fun RemoteItemDetails.Category.mapToDomain() =
    ItemDetails.Details.Category(
        name = name,
    )

private fun RemoteItemDetails.Brand.mapToDomain() =
    ItemDetails.Details.Brand(
        name = name,
    )

private fun RemoteItemDetails.ItemDescription.mapToDomain() =
    ItemDetails.Details.ItemDescription(
        productInfo = productInfo,
    )

private fun RemoteItemDetails.Color.mapToDomain() =
    ItemDetails.Details.Color(
        name = name,
        description = description,
    )

private fun RemoteItemDetails.mapColors() =
    (colors ?: emptyList())
        .map { color ->
            color.mapToDomain()
        }

private fun RemoteItemDetails.Size.mapToDomain() =
    ItemDetails.Details.Size(
        name = name,
        text = text,
    )

private fun RemoteItemDetails.mapSizes() =
    (sizes ?: emptyList())
        .map { size ->
            size.mapToDomain()
        }
