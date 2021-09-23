package com.proxer.easydo.main.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
private fun NavigationSheet(onMenuOpenChanged: () -> Unit) {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.clickable { onMenuOpenChanged() }
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_notifications),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun DrawerMenu(onBackPressed: () -> Unit) {
    Column {
        OutlinedButton(
            modifier = Modifier.align(Alignment.End).size(48.dp),
            onClick = { onBackPressed() },
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.White),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.rotate(180f)
            )
        }
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(Color.Gray, CircleShape)
                .align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Vlad Provalionok", fontSize = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        listOf(
            Pair(R.drawable.ic_menu, R.string.templates),
            Pair(R.drawable.ic_search, R.string.categories),
            Pair(R.drawable.ic_notifications, R.string.analytics),
        ).forEach {
            MenuItem(iconId = it.first, textId = it.second)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.Cyan)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Good", color = Color.LightGray)
        Text(text = "Consistancy", fontSize = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(12.dp))
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