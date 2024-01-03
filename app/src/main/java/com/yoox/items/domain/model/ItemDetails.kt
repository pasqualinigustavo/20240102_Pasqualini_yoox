package com.yoox.items.domain.model

import java.io.Serializable

data class ItemDetails(
    val cod: String,
    val brand: Details.Brand,
    val category: Details.Category,
    val price: Details.Price,
    val urlImage: String,
    val itemDescription: Details.ItemDescription?,
    val colors: List<Details.Color>?,
    val sizes: List<Details.Size>?
) : Serializable {

    sealed class Details() : Serializable {

        class Price(
            val fullPrice: String?,
            val discountedPrice: String?,
        ) {

        }

        class Category(
            val name: String?,
        ) {

        }

        class Brand(
            val name: String?,
        ) {

        }

        class ItemDescription(
            val productInfo: List<String>?,
        ) {

        }
        class Color(
            val name: String?,
            val description: String?,
        ) : Details()

        class Size(
            val name: String?,
            val text: String?,
        ) : Details()
    }

}