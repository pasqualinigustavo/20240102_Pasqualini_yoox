package com.yoox.items.presentation.history.screen

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.yoox.items.domain.model.History
import com.yoox.items.presentation.history.components.HistoryItemContainer
import com.yoox.items.presentation.items.components.LoadingContainer

@Composable
fun HistoryFragmentScreen(
    isLoadingState: Boolean,
    itemList: MutableList<History>?,
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
                    itemList = itemList,
                )
            }
        }
    )
}

@Composable
private fun BuildContent(
    itemList: MutableList<History>?,
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
            itemList = itemList,
        )
    }
}

@Composable
private fun BuildTabContent(
    itemList: MutableList<History>?,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        BuildItemsContent(
            itemList = itemList,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BuildItemsContent(
    itemList: MutableList<History>?,
) {
    val scrollState = rememberLazyListState()

    Box(
        Modifier
            .fillMaxSize()
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
                    text = stringResource(id = R.string.no_history),
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
                        HistoryItemContainer(
                            item = list[index],
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
                    }
                }
            }
        }
    }
}
