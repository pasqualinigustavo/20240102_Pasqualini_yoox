package com.yoox.items.app.api.response

import com.google.gson.annotations.SerializedName
import com.yoox.items.domain.model.Pagination
import com.yoox.items.utilities.MappableToDomain

data class RemotePagination(
    @SerializedName("SelectedPage")
    val currentPage: Int,
    @SerializedName("ItemPerPage")
    val pageSize: Int,
    @SerializedName("TotalPages")
    val totalPages: Int,
) : MappableToDomain<Pagination> {

    override fun mapToDomain() = Pagination(currentPage, totalPages, pageSize)
}
