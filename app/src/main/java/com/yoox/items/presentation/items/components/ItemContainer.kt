package com.yoox.items.presentation.items.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.yoox.items.utilities.BorderCellColor
import com.yoox.items.utilities.LightModeStandardScreensEn
import com.yoox.items.utilities.LightModeStandardScreensIt
import com.yoox.items.utilities.YooxTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun ItemContainer(
    item: Item,
    onItemTapped: (item: Item) -> Unit,
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
                .clickable { onItemTapped(item) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.spacing_10)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_16))
                ) {
                    GlideImage(
                        model = Uri.parse(item.urlImage),
                        contentDescription = "",
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.spacing_48))
                            .height(dimensionResource(id = R.dimen.spacing_48))
                            .clip(shape = CircleShape),
                    ) {
                        it.error(R.drawable.ic_launcher_foreground)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .fitCenter()
                            .load(Uri.parse(item.urlImage))
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        text = item.microCategory,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.spacing_2)))
                    DetailsText(
                        textAlign = TextAlign.Start,
                        text = item.brand,
                    )
                    Spacer(Modifier.height(dimensionResource(R.dimen.spacing_2)))
                    //TODO: Apply price logic... do not forget!
                    DetailsText(
                        textAlign = TextAlign.Start,
                        text = item.formattedFullPrice,
                    )
                }
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

//region previews
@LightModeStandardScreensEn
@LightModeStandardScreensIt
@Composable
private fun ItemContainerPreview1() {
    YooxTheme {
        ItemContainer(
            item = itemPreview,
            onItemTapped = { /*No-op for previews*/ },
        )
    }
}

@LightModeStandardScreensEn
@LightModeStandardScreensIt
@Composable
private fun ItemContainerPreview2() {
    YooxTheme {
        ItemContainer(
            item = itemPreview,
            onItemTapped = { /*No-op for previews*/ },
        )
    }
}
//endregion previews
