

package com.synctech.worksync.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.synctech.worksync.ui.components.MainScaffold
import com.synctech.worksync.ui.screens.detailPanel.JobDetailScreen
import com.synctech.worksync.ui.screens.detailPanel.JobDetailViewModel
import com.synctech.worksync.ui.screens.inventoryPanel.InventoryViewModel
import com.synctech.worksync.ui.screens.inventoryPanel.MaterialScreen
import com.synctech.worksync.ui.screens.jobsPanel.JobScreen
import com.synctech.worksync.ui.screens.jobsPanel.JobsViewModel
import com.synctech.worksync.ui.screens.loginPanel.LoginScreen
import com.synctech.worksync.ui.screens.loginPanel.LoginViewModel
import com.synctech.worksync.ui.screens.userPanel.UserPanelScreen
import com.synctech.worksync.ui.screens.userPanel.UserPanelViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            val loginViewModel = koinViewModel<LoginViewModel>()
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main/userPanel") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                viewModel = loginViewModel
            )
        }

        navigation(startDestination = "main/userPanel", route = "main") {
            composable("main/jobs") {
                MainScaffold(navController) { modifier ->
                    val viewModel = koinViewModel<JobsViewModel>()
                    JobScreen(
                        viewModel = viewModel,
                        navController = navController,
                        modifier = modifier
                    )
                }
            }
            composable("main/inventory") {
                MainScaffold(navController) { modifier ->
                    val viewModel = koinViewModel<InventoryViewModel>()
                    MaterialScreen(
                        viewModel = viewModel,
                        navController = navController,
                        modifier = modifier
                    )
                }
            }
            composable("main/userPanel") {
                MainScaffold(navController) { modifier ->
                    val viewModel = koinViewModel<UserPanelViewModel>()
                    UserPanelScreen(onLogoutSuccess = {
                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true }
                        }
                    }, viewModel = viewModel)
                }
            }


            composable("jobDetail/{jobId}") { backStackEntry ->
                val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
                val viewModel = koinViewModel<JobDetailViewModel>()
                JobDetailScreen(
                    jobDetailViewModel = viewModel,
                    jobId = jobId,
                    navController = navController
                )
            }
        }
    }
}

/*composable ("AddJobView")
      AddJobView(navController, jobsVM)
*
*
*
*
* */