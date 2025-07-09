package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.component.CoffeePercentage
import com.example.myapplication.component.CupImageSwitcher
import com.example.myapplication.component.CupSizeSwitch
import com.example.myapplication.models.CupSize

@Composable
fun CoffeeChoiceScreen(
    onBack: () -> Unit = {},
    onContinue: (String) -> Unit = { _ -> }
) {
    val cups = remember {
        listOf(
            CupSize("Small", "Small", R.drawable.cup_macchiato_s),
            CupSize("Medium", "Medium", R.drawable.cup_macchiato_m),
            CupSize("Large", "Large", R.drawable.cup_macchiato_l)
        )
    }

    var selectedSize by rememberSaveable { mutableStateOf("Medium") }
    var selectedIntensity by rememberSaveable { mutableStateOf("Medium") }
    var selectedCupIndex by rememberSaveable { mutableIntStateOf(1) }

    // Derived image size
    val cupImageSize by remember(selectedSize) {
        derivedStateOf {
            when (selectedSize) {
                "Small" -> 180.dp
                "Medium" -> 220.dp
                "Large" -> 260.dp
                else -> 220.dp
            }
        }
    }

    LaunchedEffect(selectedCupIndex) {
        selectedSize = cups[selectedCupIndex].name
    }

    LaunchedEffect(selectedSize) {
        cups.indexOfFirst { it.name == selectedSize }.takeIf { it != -1 }?.let {
            selectedCupIndex = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top bar with back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { onBack() }
            )
        }

        // Main content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Cup image switcher
            Box(
                modifier = Modifier
                    .size(360.dp, 341.dp)
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                CupImageSwitcher(
                    cups = cups,
                    imageSize = cupImageSize,
                    onCupSelected = { selectedCupIndex = it },
                    modifier = Modifier.offset(y = (-20).dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Size selector
            CupSizeSwitch(
                selectedOption = selectedSize,
                onOptionSelected = { selectedSize = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            CoffeePercentage(
                selectedOption = selectedIntensity,
                onOptionSelected = { selectedIntensity = it }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        // Continue button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    val sizeCode = when (selectedSize) {
                        "Small" -> "S"
                        "Medium" -> "M"
                        "Large" -> "L"
                        else -> "M"
                    }
                    onContinue(sizeCode)
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF222222)),
                modifier = Modifier
                    .height(48.dp)
                    .width(180.dp)
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Arrow Right",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoffeeChoiceScreenPreview() {
    CoffeeChoiceScreen()
}