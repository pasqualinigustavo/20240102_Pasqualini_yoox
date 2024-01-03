package com.yoox.items.app.api.response

import com.google.gson.annotations.SerializedName

data class RemoteItemDetails(
    @SerializedName("Cod10")
    val cod: String,

    @SerializedName("Brand")
    val brand: Brand,

    @SerializedName("Category")
    val category: Category,

    @SerializedName("FormattedPrice")
    val price: Price,

    @SerializedName("ItemDescriptions")
    val itemDescription: ItemDescription?,

    @SerializedName("Colors")
    val colors: List<Color>?,

    @SerializedName("Sizes")
    val sizes: List<Size>?,
) {

    data class Price(
        @SerializedName("FullPrice")
        val fullPrice: String?,
        @SerializedName("DiscountedPrice")
        val discountedPrice: String?,
    ) {

    }

    data class Brand(
        @SerializedName("Name")
        val name: String?,
    ) {

    }

    data class Category(
        @SerializedName("Name")
        val name: String?,
    ) {

    }

    data class Color(
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Description")
        val description: String?,
    ) {

    }

    data class Size(
        @SerializedName("Name")
        val name: String?,
        @SerializedName("Text")
        val text: String?,
    ) {

    }

    data class ItemDescription(
        @SerializedName("ProductInfo")
        val productInfo: List<String>?,
    ) {

    }

}
