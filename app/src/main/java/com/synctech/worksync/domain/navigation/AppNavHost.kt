package com.synctech.worksync.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.synctech.worksync.ui.screens.loginPanel.LoginScreen
import com.synctech.worksync.ui.screens.loginPanel.LoginViewModel
import com.synctech.worksync.ui.screens.userPanel.UserPanelScreen
import com.synctech.worksync.ui.screens.userPanel.UserPanelViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") {
            val loginViewModel = koinViewModel<LoginViewModel>()
            LoginScreen(
                onLoginSuccess = { navController.navigate("panel") },
                viewModel = loginViewModel
            )
        }

        composable("panel") {
            val userPanelViewModel = koinViewModel<UserPanelViewModel>()
            UserPanelScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }, viewModel = userPanelViewModel
            )
        }
    }
}
