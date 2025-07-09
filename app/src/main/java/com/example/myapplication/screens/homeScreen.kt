package com.example.myapplication.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.component.CoffeeButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onBringCoffeeClick: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sidebar or top bar
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top bar
                Row(
                    modifier = Modifier
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
                // Main content area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Decorative ghost images and text
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                painter = painterResource(id = R.drawable.img_dark_start),
                                contentDescription = "Ghost Decor 1",
                                modifier = Modifier
                                    .offset(x = 60.dp, y = 68.dp)
                                    .size(28.dp)
                                    .align(Alignment.TopStart)
                                    .padding(top = 8.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.img_dark_start),
                                contentDescription = "Ghost Decor 2",
                                modifier = Modifier
                                    .offset(x = (-50).dp, y = 28.dp)
                                    .size(28.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(top = 8.dp)
                            )
                        }
                        Text(
                            text = "Hocus\nPocus\nI Need Coffee\nto Focus",
                            fontSize = 28.sp,
                            color = Color(0xFF444444),
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                painter = painterResource(id = R.drawable.img_dark_start),
                                contentDescription = "Ghost Decor 3",
                                modifier = Modifier
                                    .offset(x = (-50).dp, y = (-28).dp)
                                    .size(28.dp)
                                    .align(Alignment.BottomEnd)
                                    .padding(bottom = 8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        // Ghost with shadow
                        Box(contentAlignment = Alignment.Center) {
                            Image(
                                painter = painterResource(id = R.drawable.img_nd_po),
                                contentDescription = "Ghost Coffee",
                                modifier = Modifier.size(244.dp)
                            )
                        }
                    }
                }
                // Bottom button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    CoffeeButton(
                        onBringCoffeeClick = onBringCoffeeClick
                    )
                }
            }
        }
    } else {
        // Portrait (original)
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Top bar
            Row(
                modifier = Modifier
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

            // Main content area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Decorative ghost images and text
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_dark_start),
                            contentDescription = "Ghost Decor 1",
                            modifier = Modifier
                                .offset(x = 60.dp, y = 68.dp)
                                .size(28.dp)
                                .align(Alignment.TopStart)
                                .padding(top = 8.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.img_dark_start),
                            contentDescription = "Ghost Decor 2",
                            modifier = Modifier
                                .offset(x = (-50).dp, y = 28.dp)
                                .size(28.dp)
                                .align(Alignment.TopEnd)
                                .padding(top = 8.dp)
                        )
                    }

                    Text(
                        text = "Hocus\nPocus\nI Need Coffee\nto Focus",
                        fontSize = 28.sp,
                        color = Color(0xFF444444),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_dark_start),
                            contentDescription = "Ghost Decor 3",
                            modifier = Modifier
                                .offset(x = (-50).dp, y = (-28).dp)
                                .size(28.dp)
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Ghost with shadow
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.img_nd_po),
                            contentDescription = "Ghost Coffee",
                            modifier = Modifier.size(244.dp)
                        )
                    }
                }
            }

            // Bottom button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                CoffeeButton(
                    onBringCoffeeClick = onBringCoffeeClick
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}