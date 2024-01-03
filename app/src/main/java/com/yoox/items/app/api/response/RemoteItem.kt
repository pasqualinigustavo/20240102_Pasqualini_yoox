package com.yoox.items.app.api.response

import com.google.gson.annotations.SerializedName

data class RemoteItem(
    @SerializedName("Cod10")
    val cod: String,

    @SerializedName("Brand")
    val brand: String,

    @SerializedName("MicroCategory")
    val microCategory: String,

    @SerializedName("FormattedFullPrice")
    val formattedFullPrice: String,

    @SerializedName("FormattedDiscountedPrice")
    val formattedDiscountedPrice: String,

    @SerializedName("ItemDescriptions")
    val itemDescription: ItemDescription?,

    @SerializedName("Colors")
    val colors: List<Color>?,

    @SerializedName("Sizes")
    val sizes: List<Size>?,
) {

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
