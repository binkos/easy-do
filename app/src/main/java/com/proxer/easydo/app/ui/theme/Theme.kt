package com.proxer.easydo.app.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    onPrimary = Color.White,
    primaryVariant = Color.Cyan,
    surface = OwnBlue,
    onSurface = LightBlue

)

private val LightColorPalette = lightColors(
    primary = DarkBlue,
    onPrimary = Color.White,
    primaryVariant = Purple700,
    surface = OwnBlue,
    onSurface = LightBlue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TestTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkColorPalette
    else LightColorPalette


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}