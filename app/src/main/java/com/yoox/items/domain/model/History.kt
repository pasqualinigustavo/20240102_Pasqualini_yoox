package com.yoox.items.domain.model

import java.io.Serializable

data class History(
    val cod: String,
    val brand: String,
    val microCategory: String,
    val price: String,
    val urlImage: String,
    val date: String,
) : Serializable {

    override fun equals(other: Any?): Boolean {
        val item = other as History
        return cod == item.cod
    }
}
