package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun PrepareCoffeeScreen(
    drinkType: String,
    intensity: String,
    onComplete: () -> Unit = {},
    cupSize: String,
    onBack: () -> Unit = {}
) {
    val sizeToImage = mapOf(
        "S" to R.drawable.cup_macchiato_s,
        "M" to R.drawable.cup_macchiato_m,
        "L" to R.drawable.cup_macchiato_l
    )
    val cupImage = sizeToImage[cupSize] ?: R.drawable.cup_macchiato_m

    Box(
        modifier = Modifier

            .fillMaxSize()
            .background(Color.White)

    ) {
        Column(
            modifier = Modifier
                .offset(y=150.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = cupImage),
                contentDescription = "Coffee Cup",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(224.dp))
            Box(
                modifier = Modifier
                    .size(202.dp, 105.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_almost_done),
                    contentDescription = "Timer Icon",
                )
            }
        }
    }
}