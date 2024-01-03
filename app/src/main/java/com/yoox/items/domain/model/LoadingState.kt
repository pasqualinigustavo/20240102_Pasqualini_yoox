package com.yoox.items.domain.model

enum class LoadingState {
    LOADING, ERROR, READY, NONE;

    fun isLoading(): Boolean = this == LOADING
    fun isError(): Boolean = this == ERROR
    fun isReady(): Boolean = this == READY
    fun isNone(): Boolean = this == NONE
}
