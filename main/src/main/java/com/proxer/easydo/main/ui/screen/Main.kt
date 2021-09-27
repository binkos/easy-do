package com.proxer.easydo.main.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.proxer.easydo.main.R

@Composable
fun MainScreen() {
    var isCollapsed by remember { mutableStateOf(true) }
    BoxWithConstraints(modifier = Modifier.background(MaterialTheme.colors.primary)) {
        val constraintsScope = this
        val widthDp = with(LocalDensity.current) { constraintsScope.maxWidth }
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(vertical = animateDpAsState(targetValue = if (isCollapsed) 0.dp else 16.dp).value)
                .offset(
                    x = animateDpAsState(
                        targetValue = if (isCollapsed) 0.dp else widthDp.times(0.7f)
                    ).value
                )
                .clip(RoundedCornerShape(animateDpAsState(targetValue = (if (isCollapsed) 0 else 16).dp).value))
                .background(MaterialTheme.colors.surface)
                .fillMaxSize()

        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.categories).uppercase()
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                item { Spacer(Modifier) }
                item { Category(12, 6, "Business", Color.Black) }
                item { Category(8, 7, "Personal", Color.Cyan) }
                item { Category(5, 1, "Family", Color.Red) }
                item { Category(2, 0, "Study", Color.Magenta) }
                item { Category(10, 4, "GirlFriend", Color.Yellow) }
                item { CreateNewCategory() }
                item { Spacer(Modifier) }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.todays_tasks).uppercase()
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                items(5) {
                    Task(false, Color.Yellow, "Task Name")
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            NavigationSheet { isCollapsed = !isCollapsed }
        }

        Box(
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp, bottom = 24.dp)
                .zIndex(0f)
                .fillMaxWidth(0.60f)
                .fillMaxHeight()
        ) {
            DrawerMenu { isCollapsed = true }
        }
    }
}

@Composable
fun MenuItem(
    @DrawableRes iconId: Int,
    @StringRes textId: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = iconId), contentDescription = null)
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = stringResource(id = textId), fontSize = 18.sp)
    }
}