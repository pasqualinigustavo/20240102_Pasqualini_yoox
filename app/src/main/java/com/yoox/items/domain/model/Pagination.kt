package com.yoox.items.domain.model

data class Pagination(
    val currentPage: Int,
    val totalPages: Int,
    val pageSize: Int,
)
