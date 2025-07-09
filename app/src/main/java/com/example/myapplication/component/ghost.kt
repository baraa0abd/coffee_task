package com.example.myapplication.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun GhostWithShadow(
    ghostImageRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = ghostImageRes),
            contentDescription = "Ghost Coffee",
            modifier = Modifier.size(80.dp)
        )
    }
}

@Composable
fun GhostColumn() {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GhostWithShadow(
            ghostImageRes = R.drawable.img_nd_po
        )
        GhostWithShadow(
            ghostImageRes = R.drawable.img_st_po
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GhostColumnPreview() {
    GhostColumn()
}  