package com.yoox.items.presentation.items.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingContainer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        content()
        if (isLoading) {
            LoadingView()
        }
    }
}

@Composable
private fun SetTransparentOverlay(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5F))
    ) {}
}

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color(1f, 1f, 1f, 0.5f))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { }
    ) {
        CircularProgressIndicator(color = Color.Black)
    }
}
