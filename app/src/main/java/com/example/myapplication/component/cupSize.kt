package com.example.myapplication.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.models.CupSize
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.absoluteValue

@Composable
fun CupImageSwitcher(
    cups: List<com.example.myapplication.models.CupSize>,
    modifier: Modifier = Modifier,
    imageSize: Dp,
    onCupSelected: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current.density

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
                onCupSelected(index)
            }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = (360.dp - imageSize) / 2f),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(cups.size) { index ->
            val cup = cups[index]
            val center = lazyListState.layoutInfo.viewportEndOffset / 2
            val itemInfo = lazyListState.layoutInfo.visibleItemsInfo.find { it.index == index }
            val itemWidthPx = imageSize.value * density

            val scale = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 2f)).coerceIn(0f, 0.4f))
            } else { 0.6f }

            val alpha = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 1.5f)).coerceIn(0f, 0.6f))
            } else { 0.4f }

            Image(
                painter = painterResource(R.drawable.cup_macchiato_l),
                contentDescription = cup.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(imageSize)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            )
        }
    }
}

@Composable
fun CupSizeSwitch(
    modifier: Modifier = Modifier,
    selectedOption: String = "Small",
    onOptionSelected: (String) -> Unit = {}
) {
    val options = listOf("Small", "Medium", "Large")

    Box(
        modifier = modifier
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onOptionSelected(option) }
                ) {

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = if (isSelected) Color(0xFF8B4513) else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { onOptionSelected(option) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option.first().toString(),
                            color = if (isSelected) Color.White else Color(0xFF888888),
                            fontSize = if (isSelected) 20.sp else 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = option,
                        color = if (isSelected) Color.Black else Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun CupSizeSwitcherScreen(
    onBack: () -> Unit = {}
) {
    var selectedSize by remember { mutableStateOf("Medium") }
    var selectedCupIndex by remember { mutableIntStateOf(1) }

    val cupImageSize = remember(selectedSize) {
        derivedStateOf {
            when (selectedSize) {
                "Small" -> 180.dp
                "Medium" -> 220.dp
                "Large" -> 260.dp
                else -> 220.dp
            }
        }
    }

    val cups = remember {
        listOf(
            CupSize("Small", "Small", R.drawable.cup_macchiato_s),
            CupSize("Medium", "Medium", R.drawable.cup_macchiato_m),
            CupSize("Large", "Large", R.drawable.cup_macchiato_l)
        )
    }

    LaunchedEffect(selectedCupIndex) {
        selectedSize = cups[selectedCupIndex].name
    }

    LaunchedEffect(selectedSize) {
        val newIndex = cups.indexOfFirst { it.name == selectedSize }
        if (newIndex != -1 && newIndex != selectedCupIndex) {
            selectedCupIndex = newIndex
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            CupImageSwitcher(
                cups = cups,
                imageSize = cupImageSize.value,
                onCupSelected = { index ->
                    selectedCupIndex = index
                },
                modifier = Modifier.offset(y = (-50).dp)
            )
        }

        // Size selector at bottom
        CupSizeSwitch(
            selectedOption = selectedSize,
            onOptionSelected = { newSize ->
                selectedSize = newSize
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
                .padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CupSizeSwitcherScreenPreview() {
    CupSizeSwitcherScreen()
}