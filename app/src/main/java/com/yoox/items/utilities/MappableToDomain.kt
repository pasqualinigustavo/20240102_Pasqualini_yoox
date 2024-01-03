package com.yoox.items.utilities

interface MappableToDomain<T> {
    fun mapToDomain(): T?
}
