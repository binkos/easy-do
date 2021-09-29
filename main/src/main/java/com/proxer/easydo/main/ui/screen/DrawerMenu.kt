package com.proxer.easydo.main.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proxer.easydo.main.R

@Composable
fun DrawerMenu(onBackPressed: () -> Unit) {
    Column {
        OutlinedButton(
            modifier = Modifier
                .align(Alignment.End)
                .size(48.dp),
            onClick = { onBackPressed() },
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.White),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent)
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
        Text(text = "Good")
        Text(text = "Consistancy", fontSize = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun MenuItem(
    @DrawableRes iconId: Int,
    @StringRes textId: Int
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(id = iconId), contentDescription = null)
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = stringResource(id = textId), fontSize = 18.sp)
    }
}