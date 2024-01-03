package com.yoox.items.utilities.extensions

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
