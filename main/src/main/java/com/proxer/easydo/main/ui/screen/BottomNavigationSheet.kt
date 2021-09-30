package com.proxer.easydo.main.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.proxer.easydo.main.R

@Composable
fun NavigationSheet(
    onAddButtonPressed: () -> Unit,
    onMenuOpenChanged: () -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp)) {
        Icon(
            modifier = Modifier
                .clickable { onAddButtonPressed() }
                .align(Alignment.CenterHorizontally)
                .size(24.dp),
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null,
            tint = Color.White
        )
        Row {
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
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}