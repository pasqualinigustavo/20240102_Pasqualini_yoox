package com.yoox.items.presentation.itemDetails.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.yoox.items.R
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.presentation.itemDetails.components.ItemDetailsContainer
import com.yoox.items.presentation.itemDetails.components.itemDetailsPreview
import com.yoox.items.presentation.items.components.LoadingContainer
import com.yoox.items.utilities.LightModeStandardScreensEn
import com.yoox.items.utilities.LightModeStandardScreensIt
import com.yoox.items.utilities.YooxTheme

@Composable
fun ItemDetailsFragmentScreen(
    isLoadingState: Boolean,
    loadingState: LoadingState,
    item: ItemDetails?,
) {

    var mainModifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)

    if (loadingState.isReady()) {
        mainModifier = mainModifier.then(Modifier.verticalScroll(state = rememberScrollState()))
    }

    Scaffold(
        modifier = mainModifier,
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
        } ?: run {
            Text(
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                text = stringResource(id = R.string.no_item),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
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
            loadingState = LoadingState.READY,
            isLoadingState = false,
        )
    }
}
