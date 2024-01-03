package com.yoox.items.presentation.items.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.yoox.items.R
import com.yoox.items.domain.model.Item
import com.yoox.items.presentation.items.components.ItemContainer
import com.yoox.items.presentation.items.components.LoadingContainer
import com.yoox.items.utilities.extensions.isScrolledToTheEnd

@Composable
fun ItemsFragmentScreen(
    isLoadingState: Boolean,
    itemList: MutableList<Item>?,
    isRefreshingItemListState: Boolean,
    onRefreshItemList: () -> Unit,
    onItemTapped: (item: Item) -> Unit,
    onBottomListReached: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        snackbarHost = { /* NO-OP */ },
        containerColor = MaterialTheme.colorScheme.background,
        content = { innerPadding ->
            LoadingContainer(
                isLoading = isLoadingState,
                modifier = Modifier.padding(innerPadding),
            ) {
                BuildContent(
                    isRefreshingItemListState = isRefreshingItemListState,
                    onRefreshItemList = { onRefreshItemList() },
                    itemList = itemList,
                    onBottomListReached = { onBottomListReached() },
                    onItemTapped = { item ->
                        onItemTapped(item)
                    },
                )
            }
        }
    )
}

@Composable
private fun BuildContent(
    isRefreshingItemListState: Boolean,
    onRefreshItemList: () -> Unit,
    itemList: MutableList<Item>?,
    onBottomListReached: () -> Unit,
    onItemTapped: (item: Item) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = dimensionResource(id = R.dimen.spacing_20),
            )
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_10)))
        BuildTabContent(
            isRefreshingItemListState = isRefreshingItemListState,
            onRefreshItemList = { onRefreshItemList() },
            itemList = itemList,
            onBottomListReached = { onBottomListReached() },
            onItemTapped = { item ->
                onItemTapped(item)
            },
        )
    }
}

@Composable
private fun BuildTabContent(
    isRefreshingItemListState: Boolean,
    onRefreshItemList: () -> Unit,
    itemList: MutableList<Item>?,
    onBottomListReached: () -> Unit,
    onItemTapped: (item: Item) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        BuildItemsContent(
            isRefreshingItemListState = isRefreshingItemListState,
            onRefreshItemList = { onRefreshItemList() },
            itemList = itemList,
            onBottomListReached = { onBottomListReached() },
            onItemTapped = { item ->
                onItemTapped(item)
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BuildItemsContent(
    isRefreshingItemListState: Boolean,
    onRefreshItemList: () -> Unit,
    itemList: MutableList<Item>?,
    onBottomListReached: () -> Unit,
    onItemTapped: (item: Item) -> Unit,
) {
    val scrollState = rememberLazyListState()

    val pullRefreshState =
        rememberPullRefreshState(isRefreshingItemListState, { onRefreshItemList() })

    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        if (itemList?.isEmpty() == true) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp,
                    text = stringResource(id = R.string.no_items),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        } else {
            itemList?.let { list ->
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.spacing_10))
                ) {
                    items(list.size) { index ->
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.spacing_5))
                        )
                        ItemContainer(
                            item = list[index],
                            onItemTapped = { item ->
                                onItemTapped(item)
                            },
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshingItemListState,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

    // observer when reached end of list
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToTheEnd()
        }
    }

    // act when end of list reached
    LaunchedEffect(endOfListReached) {
        onBottomListReached()
    }
}
