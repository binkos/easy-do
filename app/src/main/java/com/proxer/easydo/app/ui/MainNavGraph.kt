package com.proxer.easydo.app.ui

import com.proxer.easydo.onboarding.ui.screen.Onboarding
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.proxer.easydo.main.ui.MainViewModel
import com.proxer.easydo.main.ui.screen.MAIN_DESTINATION
import com.proxer.easydo.main.ui.screen.MainScreen
import com.proxer.easydo.onboarding.ui.screen.ONBOARDING_DESTINATION
import com.proxer.easydo.signin.ui.screen.SIGN_IN_DESTINATION
import com.proxer.easydo.signin.ui.screen.SignIn

@Composable
fun MainNavGraph(onThemeChanged: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val actions = Actions(navController)

    NavHost(navController = navController, startDestination = ONBOARDING_DESTINATION) {
        composable(ONBOARDING_DESTINATION) { Onboarding(actions.onOnboardingFinished) }
        composable(SIGN_IN_DESTINATION) { SignIn(actions.onRegistered) }
        composable(MAIN_DESTINATION) {
            val viewModel = viewModel<MainViewModel>()
            MainScreen(viewModel)
        }
    }
}

class Actions(private val controller: NavController) {

    val onOnboardingFinished: () -> Unit = {
        controller.navigate(SIGN_IN_DESTINATION) { popUpTo(0) }
    }

    val onRegistered: () -> Unit = {
        controller.navigate(MAIN_DESTINATION) { popUpTo(0) }
    }
}