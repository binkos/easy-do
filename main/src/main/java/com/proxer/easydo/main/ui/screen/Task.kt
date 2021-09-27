package com.proxer.easydo.main.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.proxer.easydo.main.R

@Composable
fun Task(
    isCompleted: Boolean,
    color: Color,
    taskName: String
) {
    var isCompletedState by remember { mutableStateOf(isCompleted) }
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(horizontal = 8.dp)
            .height(48.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompletionIndicator(isCompleted = isCompletedState, color = color) {
            isCompletedState = !isCompletedState
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = taskName,
            textDecoration = if (isCompletedState) TextDecoration.LineThrough else null
        )
    }
}

@Composable
fun CompletionIndicator(
    isCompleted: Boolean,
    color: Color,
    indicatorClickListener: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable { indicatorClickListener() }
            .size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(24.dp)) {
            drawCircle(
                SolidColor(color),
                style = Stroke(4f)
            )
            if (isCompleted) {
                drawOval(color)
            }
        }
        // TODO rewrite to canvas
        if (isCompleted) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = R.drawable.ic_check_vote),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}