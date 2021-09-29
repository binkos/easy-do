package com.proxer.easydo.main.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.proxer.easydo.main.R
import com.proxer.easydo.main.ui.color.IndicatorColor
import com.proxer.easydo.main.ui.color.LightPurple
import com.proxer.easydo.main.ui.model.Category

@Composable
fun Category(model: Category) {
    Column(
        Modifier
            .width(180.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .shadow(elevation = 4.dp)
            .background(MaterialTheme.colors.primary)
            .padding(start = 8.dp, end = 8.dp, top = 6.dp, bottom = 8.dp)
    ) {
        Text(text = stringResource(R.string.tasks_count, model.allTasks), color = LightPurple)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = model.name,
            color = Color.White,
            maxLines = 2
        )
        Spacer(modifier = Modifier.weight(1f))
        CategoryProgress(
            allCount = model.allTasks,
            currentEnded = model.completedTasks,
            color = Color(model.color)
        )
    }
}

@Composable
private fun CategoryProgress(allCount: Int, currentEnded: Int, color: Color) {
    Box {
        Box(
            Modifier
                .padding(top = 4.dp, start = 1.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(IndicatorColor)
                .height(4.dp)
                .fillMaxWidth()
        )
        Box(
            Modifier
                .height(8.dp)
                .fillMaxWidth(
                    animateFloatAsState(targetValue = currentEnded.toFloat() / allCount.toFloat())
                        .value
                )
        ) {
            Box(
                Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = color)
                    .height(4.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
            if (currentEnded != 0)
                Box(
                    Modifier
                        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                        .background(color = color)
                        .width(4.dp)
                        .height(8.dp)
                        .align(Alignment.CenterEnd)
                )
        }
    }
}