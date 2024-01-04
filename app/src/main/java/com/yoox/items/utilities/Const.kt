package com.yoox.items.utilities

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object Const {

    val requestDispatchers: CoroutineDispatcher = Dispatchers.IO
    const val EMPTY_RESPONSE_ERROR = "Response body is empty or null"
    const val EMPTY = ""
    const val HISTORY_ITEMS = "HISTORY_ITEMS"
}
