package com.example.myapplication.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

//didn't imp the screen yet

@Composable
fun CoffeeToggleSwitch(
    modifier: Modifier = Modifier,
    isOn: Boolean = false,
    onToggle: (Boolean) -> Unit = {},
    cupType: String = "cappuccino"
) {
    val backgroundColor = if (isOn) Color(0xFF8B4513) else Color(0xFFF5F5F5)
    val textColor = if (isOn) Color.White else Color.Black

    val cupImageMap = mapOf(
        "espresso" to R.drawable.exp_top,
        "cappuccino" to R.drawable.cap_top,
        "latte" to R.drawable.lat_top,
        "mocha" to R.drawable.moc_top

    )
    val cupImageRes = cupImageMap[cupType] ?: R.drawable.cap_top

    val cupOffset: Dp = animateDpAsState(targetValue = if (isOn) 84.dp else 4.dp, label = "cupOffset").value

    Box(
        modifier = modifier
            .width(140.dp)
            .height(56.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(28.dp)
            )
            .clickable { onToggle(!isOn) }
            .padding(4.dp)
    ) {

    CoffeeCircle(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = cupOffset),
            cupImageRes = cupImageRes
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!isOn) {
                Spacer(modifier = Modifier.width(56.dp))
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "OFF",
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 12.dp)
                )
            } else {
                Text(
                    text = "ON",
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(56.dp))
            }
        }
    }
}

@Composable
fun CoffeeCircle(modifier: Modifier = Modifier, cupImageRes: Int = R.drawable.cap_top) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = cupImageRes),
            contentDescription = "Coffee Cup Top",
            modifier = Modifier.size(40.dp)
        )
    }
}