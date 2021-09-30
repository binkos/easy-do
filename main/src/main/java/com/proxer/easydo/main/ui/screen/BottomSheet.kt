package com.proxer.easydo.main.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheet(onCrossPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onCrossPressed() }
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.15f)
                .align(Alignment.CenterHorizontally)
        ) {
            drawLine(
                color = Color.LightGray,
                start = Offset.Zero,
                end = Offset(size.width, 0f),
                strokeWidth = 6f
            )
        }
//        Text(text = "")
    }
}