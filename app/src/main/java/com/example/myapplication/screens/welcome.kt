package com.example.myapplication.screens

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.models.DrinkType

@Composable
fun WelcomeScreen(
    onProfileClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
) {
    val drinks = remember {
        listOf(
            DrinkType("Coffee", R.drawable.mocchiato),
            DrinkType("Latte", R.drawable.americano),
            DrinkType("Cappuccino", R.drawable.espresso),
            DrinkType("Espresso", R.drawable.img_7)
        )
    }

    var selectedDrinkIndex by rememberSaveable { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        val isLandscape = maxWidth > maxHeight

        if (isLandscape) {
            LandscapeLayout(
                drinks = drinks,
                selectedDrinkIndex = selectedDrinkIndex,
                onProfileClick = onProfileClick,
                onAddClick = onAddClick,
                onContinueClick = onContinueClick,
                onDrinkSelected = { selectedDrinkIndex = it }
            )
        } else {
            PortraitLayout(
                drinks = drinks,
                selectedDrinkIndex = selectedDrinkIndex,
                onProfileClick = onProfileClick,
                onAddClick = onAddClick,
                onContinueClick = onContinueClick,
                onDrinkSelected = { selectedDrinkIndex = it }
            )
        }
    }
}

@Composable
private fun LandscapeLayout(
    drinks: List<DrinkType>,
    selectedDrinkIndex: Int,
    onProfileClick: () -> Unit,
    onAddClick: () -> Unit,
    onContinueClick: () -> Unit,
    onDrinkSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top navigation bar
            Row(
                modifier = Modifier
                    .offset(y = 26.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_prof_pic),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable { onProfileClick() }
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5))
                        .clickable { onAddClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add",
                        tint = Color.Black
                    )
                }
            }

            // Main content
            Column(
                modifier = Modifier
                    .offset(y = 100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = "Good Morning",
                    fontSize = 28.sp,
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF222222),
                                fontSize = 32.sp
                            )
                        ) {
                            append("Hamsa ")
                        }
                        withStyle(SpanStyle(fontSize = 28.sp)) {
                            append("\uD83C\uDF1E")
                        }
                    },
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "What would you like to drink today?",
                    fontSize = 16.sp,
                    color = Color(0xFF444444),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }

            // Continue button
            Box(
                modifier = Modifier
                    .width(162.dp)
                    .height(56.dp)
                    .absoluteOffset(x = 115.dp, y = 674.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(100.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onContinueClick,
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

        // Drink image switcher
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(bottom = 50.dp),
            contentAlignment = Alignment.Center
        ) {
            DrinkImageSwitcher(
                drinks = drinks,
                modifier = Modifier
                    .offset(y = 60.dp)
                    .height(300.dp),
                onDrinkSelected = onDrinkSelected
            )
        }
    }
}

@Composable
private fun PortraitLayout(
    drinks: List<DrinkType>,
    selectedDrinkIndex: Int,
    onProfileClick: () -> Unit,
    onAddClick: () -> Unit,
    onContinueClick: () -> Unit,
    onDrinkSelected: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top navigation bar
        Row(
            modifier = Modifier
                .offset(y = 26.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_prof_pic),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { onProfileClick() }
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
                    .clickable { onAddClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add",
                    tint = Color.Black
                )
            }
        }

        // Main content
        Column(
            modifier = Modifier
                .offset(y = 100.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // Greeting
            Text(
                text = "Good Morning",
                fontSize = 28.sp,
                color = Color(0xFF888888),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF222222),
                            fontSize = 32.sp
                        )
                    ) {
                        append("Hamsa ")
                    }
                    withStyle(SpanStyle(fontSize = 28.sp)) {
                        append("\uD83C\uDF1E") // sun emoji
                    }
                },
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "What would you like to drink today?",
                fontSize = 16.sp,
                color = Color(0xFF444444),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }

        // Drink image switcher
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            DrinkImageSwitcher(
                drinks = drinks,
                modifier = Modifier
                    .offset(y = 60.dp)
                    .height(300.dp),
                onDrinkSelected = onDrinkSelected
            )
        }

        // Continue button
        Box(
            modifier = Modifier
                .width(162.dp)
                .height(56.dp)
                .absoluteOffset(x = 115.dp, y = 674.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = onContinueClick,
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
fun PreviewWelcomeScreen() {
    WelcomeScreen()
}