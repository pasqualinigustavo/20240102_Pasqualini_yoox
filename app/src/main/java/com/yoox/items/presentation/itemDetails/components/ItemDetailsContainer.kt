package com.yoox.items.presentation.itemDetails.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yoox.items.R
import com.yoox.items.domain.model.Item
import com.yoox.items.domain.model.ItemDetails
import com.yoox.items.presentation.items.components.itemPreview
import com.yoox.items.utilities.BorderCellColor
import com.yoox.items.utilities.LightModeStandardScreensEn
import com.yoox.items.utilities.LightModeStandardScreensIt
import com.yoox.items.utilities.YooxTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemDetailsContainer(
    item: ItemDetails,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.spacing_16),
                start = dimensionResource(id = R.dimen.spacing_16),
                end = dimensionResource(id = R.dimen.spacing_16),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                )
                .border(
                    width = dimensionResource(id = R.dimen.spacing_1),
                    color = BorderCellColor,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.spacing_10)),
            ) {
                GlideImage(
                    model = Uri.parse(item.urlImage),
                    contentDescription = "",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.spacing_190))
                        .height(dimensionResource(id = R.dimen.spacing_190))
                        .clip(shape = CircleShape),
                ) {
                    it.error(R.mipmap.ic_launcher)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .fitCenter()
                        .load(Uri.parse(item.urlImage))
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.spacing_10)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    text = item.category.name ?: "",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_2)))
                DetailsText(
                    textAlign = TextAlign.Start,
                    text = item.brand.name ?: "",
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.spacing_2)))
                DetailsText(
                    textAlign = TextAlign.Start,
                    text = item.getPrice() ?: "",
                )
                val colors = item.colors?.joinToString {
                    it.name ?: ""
                }
                val sizes = item.sizes?.joinToString {
                    it.name ?: ""
                }
                BuildItemDetails(stringResource(id = R.string.product_info), item.itemDescription?.productInfo?.toString() ?: "")
                BuildItemDetails(stringResource(id = R.string.product_colors), colors.toString())
                BuildItemDetails(stringResource(id = R.string.product_sizes), sizes.toString())
            }
        }
    }
}

@Composable
fun DetailsText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    text: String,
    fontSize: TextUnit = 10.sp,
) {
    androidx.compose.material.Text(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Light,
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BuildItemDetails(
    title: String,
    content: String
) {
    Column(
    ) {
        Text(
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.spacing_5))
        )
        Text(
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            text = content,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_5)))
    }
}


//region previews
@LightModeStandardScreensEn
@LightModeStandardScreensIt
@Composable
private fun ItemContainerPreview1() {
    YooxTheme {
        ItemDetailsContainer(
            item = itemDetailsPreview,
        )
    }
}

@LightModeStandardScreensEn
@LightModeStandardScreensIt
@Composable
private fun ItemContainerPreview2() {
    YooxTheme {
        ItemDetailsContainer(
            item = itemDetailsPreview,
        )
    }
}
//endregion previews
