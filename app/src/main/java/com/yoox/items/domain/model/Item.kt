package com.yoox.items.domain.model

import java.io.Serializable

data class Item(
    val cod: String,
    val brand: String,
    val microCategory: String,
    val fullPrice: Double,
    val discountedPrice: Double,
    val formattedFullPrice: String,
    val formattedDiscountedPrice: String,
    val urlImage: String,
    val itemDescription: Details.ItemDescription?,
    val colors: List<Details.Color>?,
    val sizes: List<Details.Size>?
) : Serializable {

    fun getPrice() : String {
        return if(fullPrice == discountedPrice)
            formattedFullPrice
        else formattedDiscountedPrice
    }
    sealed class Details() : Serializable {

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
