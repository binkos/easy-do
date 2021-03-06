package com.proxer.easydo.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.proxer.easydo.app.ui.theme.TestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(false) }
            TestTheme(isDarkTheme = isDarkTheme) {
                Surface {
                    MainNavGraph(
                        finishActivity = { finish() },
                        onThemeChanged = { isDarkTheme = it }
                    )
                }
            }
        }
    }
}