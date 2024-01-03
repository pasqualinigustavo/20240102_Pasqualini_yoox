package com.yoox.items.utilities.extensions

import com.yoox.items.domain.model.Pagination

data class PaginatedList<T>(val pagination: Pagination) : ArrayList<T>()

fun <T> List<T>.toPaginatedList(pagination: Pagination): PaginatedList<T> {
    return PaginatedList<T>(pagination).let {
        it.addAll(this)
        it
    }
}
