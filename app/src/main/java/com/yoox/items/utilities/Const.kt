package com.yoox.items.utilities

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Const {

    val requestDispatchers: CoroutineDispatcher = Dispatchers.IO
    val mainRequestDispatchers: CoroutineDispatcher = Dispatchers.Main
    const val EMPTY_RESPONSE_ERROR = "Response body is empty or null"

    const val ITEM_BUNDLE = "ITEM"
}
