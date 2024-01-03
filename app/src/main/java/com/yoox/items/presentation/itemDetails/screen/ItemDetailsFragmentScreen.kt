package com.yoox.items.presentation.itemDetails.screen

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
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.presentation.itemDetails.components.ItemDetailsContainer
import com.yoox.items.presentation.itemDetails.components.itemDetailsPreview
import com.yoox.items.presentation.items.components.ItemContainer
import com.yoox.items.presentation.items.components.LoadingContainer
import com.yoox.items.utilities.LightModeStandardScreensEn
import com.yoox.items.utilities.LightModeStandardScreensIt
import com.yoox.items.utilities.YooxTheme
import com.yoox.items.utilities.extensions.isScrolledToTheEnd

@Composable
fun ItemDetailsFragmentScreen(
    isLoadingState: Boolean,
    item: ItemDetails?,
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
                    item = item,
                )
            }
        }
    )
}

@Composable
private fun BuildContent(
    item: ItemDetails?,
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
            item = item,
        )
    }
}

@Composable
private fun BuildTabContent(
    item: ItemDetails?,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BuildItemsContent(
            item = item,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BuildItemsContent(
    item: ItemDetails?,
) {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.spacing_5))
        )
        item?.let {
            ItemDetailsContainer(
                item = item,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
        }
    }
}

//region previews
@LightModeStandardScreensEn
@LightModeStandardScreensIt
@Composable
private fun ItemDetailsPreview() {
    YooxTheme {
        ItemDetailsFragmentScreen(
            item = itemDetailsPreview,
            isLoadingState = false,
        )
    }
}
