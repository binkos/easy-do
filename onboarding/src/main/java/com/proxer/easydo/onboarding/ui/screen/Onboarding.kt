package com.proxer.easydo.onboarding.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Onboarding(onOnboardingFinished: () -> Unit) {
    Box(
        modifier = Modifier
            .background(Color.Cyan)
            .clickable { onOnboardingFinished() }
            .fillMaxSize()
    )
}