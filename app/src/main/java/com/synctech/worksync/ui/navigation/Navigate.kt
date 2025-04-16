package com.synctech.worksync.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.synctech.worksync.ui.models.WorkUIModel
import com.synctech.worksync.ui.screens.detailPanel.WorkDetailScreen
import com.synctech.worksync.ui.screens.workPanel.WorkScreen
import com.synctech.worksync.ui.screens.workPanel.WorkViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: WorkViewModel) {
    NavHost(
        navController = navController,
        startDestination = "workList"
    ) {
        composable("workList") {
            WorkScreen(viewModel = viewModel, navController = navController)
        }

        composable(
            route = "workDetail/{jobName}/{clientName}/{description}/{address}/{assignedTo}",
            arguments = listOf(
                navArgument("jobName") { type = NavType.StringType },
                navArgument("clientName") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType },
                navArgument("assignedTo") { type = NavType.StringType; nullable = true }
            )
        ) { backStackEntry ->
            val jobName = backStackEntry.arguments?.getString("jobName") ?: ""
            val clientName = backStackEntry.arguments?.getString("clientName") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            val address = backStackEntry.arguments?.getString("address") ?: ""
            val assignedTo = backStackEntry.arguments?.getString("assignedTo")

            /*WorkDetailScreen(
                work = WorkUIModel(
                    jobName = jobName,
                    clientName = clientName,
                    description = description,
                    address = address,
                    assignedTo = assignedTo
                )
            )*/
        }
    }
}
