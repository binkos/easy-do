package com.proxer.easydo.main.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.proxer.easydo.main.R

@Composable
fun Category(
    allTasksCount: Int,
    completedTasksCount: Int,
    categoryName: String,
    color: Color
) {
    Column(
        Modifier
            .width(180.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .shadow(elevation = 4.dp)
            .background(Color.LightGray)
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(text = stringResource(R.string.tasks_count, allTasksCount))
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = categoryName)
        Spacer(modifier = Modifier.weight(1f))
        CategoryProgress(
            allCount = allTasksCount,
            currentEnded = completedTasksCount,
            color = color
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
                .background(Color.DarkGray)
                .height(4.dp)
                .fillMaxWidth()
        )
        Box(
            Modifier
                .height(8.dp)
                .fillMaxWidth(currentEnded.toFloat() / allCount.toFloat())
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


@Composable
fun CreateNewCategory() {
    Column(
        Modifier
            .width(180.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null
        )
    }
}