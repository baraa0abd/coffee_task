package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.DrinkType
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.absoluteValue

@Composable
fun DrinkImageSwitcher(
    drinks: List<DrinkType>,
    modifier: Modifier = Modifier,
    onDrinkSelected: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current.density
    val itemWidthDp = 200.dp
    val itemWidthPx = itemWidthDp.value * density

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val center = lazyListState.layoutInfo.viewportEndOffset / 2
            val closestItem = lazyListState.layoutInfo.visibleItemsInfo.minByOrNull {
                (it.offset + it.size / 2 - center).absoluteValue
            }
            if (closestItem != null) {
                lazyListState.animateScrollToItem(closestItem.index)
            }
        }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                onDrinkSelected(index)
            }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = (360.dp - itemWidthDp) / 2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(drinks.size) { index ->
            val drink = drinks[index]
            val center = lazyListState.layoutInfo.viewportEndOffset / 2
            val itemInfo = lazyListState.layoutInfo.visibleItemsInfo.find { it.index == index }

            // Calculate scale and alpha based on distance from center
            val scale = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 2f)).coerceIn(0f, 0.4f))
            } else {
                0.6f // Default scale for items not visible
            }

            val alpha = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 1.5f)).coerceIn(0f, 0.6f))
            } else {
                0.4f
            }

            Image(
                painter = painterResource(id = drink.drawableRes),
                contentDescription = drink.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(itemWidthDp, 298.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            )
        }
    }
}