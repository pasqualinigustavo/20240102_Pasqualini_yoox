package com.yoox.items.app.api.response

import com.google.gson.annotations.SerializedName

data class RemoteItem(
    @SerializedName("Cod10")
    val cod: String,

    @SerializedName("Brand")
    val brand: String,

    @SerializedName("MicroCategory")
    val microCategory: String,

    @SerializedName("FullPrice")
    val fullPrice: Double,

    @SerializedName("DiscountedPrice")
    val discountedPrice: Double,

    @SerializedName("FormattedFullPrice")
    val formattedFullPrice: String,

    @SerializedName("FormattedDiscountedPrice")
    val formattedDiscountedPrice: String,

    @SerializedName("ItemDescriptions")
    val itemDescription: ItemDescription?,
) {

    data class ItemDescription(
        @SerializedName("ProductInfo")
        val productInfo: List<String>?,
    ) {

    }

}
