package com.proxer.easydo.signin.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SignIn(onRegistered: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Magenta)
            .clickable { onRegistered() }
            .fillMaxSize()
    )
}