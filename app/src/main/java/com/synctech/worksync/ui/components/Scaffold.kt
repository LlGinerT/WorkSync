package com.synctech.worksync.ui.components


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.synctech.worksync.data.testData.MockJobsDataRepository
import com.synctech.worksync.domain.navigation.BottomNavItem
import com.synctech.worksync.domain.useCases.GetJobsUseCase
import com.synctech.worksync.ui.screens.jobsPanel.JobScreen
import com.synctech.worksync.ui.screens.jobsPanel.JobsViewModel
import com.synctech.worksync.ui.session.SessionViewModel


@Composable
fun ScreenWithScaffold(
    currentRoute: String,
    navController: NavController,
    items: List<BottomNavItem>,
    sessionViewModel: SessionViewModel
) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        when (currentRoute) {
            "JobList" -> JobScreen(
                viewModel = JobsViewModel(
                    getJobsUseCase = GetJobsUseCase(
                        jobsRepository = MockJobsDataRepository()
                    ), sessionViewModel = sessionViewModel
                ), navController = navController
            )

            "inventory" -> {}
            "userPanel" -> {}
        }
    }
}

