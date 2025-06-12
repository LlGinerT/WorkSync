package com.synctech.worksync.di

import com.synctech.worksync.di.Qualifiers.activeSession
import com.synctech.worksync.di.Qualifiers.authUser
import com.synctech.worksync.di.Qualifiers.inventoryMediator
import com.synctech.worksync.di.Qualifiers.jobByID
import com.synctech.worksync.di.Qualifiers.jobMediator
import com.synctech.worksync.di.Qualifiers.logout
import com.synctech.worksync.di.Qualifiers.updateSession
import com.synctech.worksync.ui.screens.addJobPanel.AddJobViewModel
import com.synctech.worksync.ui.screens.detailPanel.JobDetailViewModel
import com.synctech.worksync.ui.screens.inventoryPanel.InventoryViewModel
import com.synctech.worksync.ui.screens.jobsPanel.JobsViewModel
import com.synctech.worksync.ui.screens.loginPanel.LoginViewModel
import com.synctech.worksync.ui.screens.userPanel.UserPanelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(authUser)) }
    viewModel {
        UserPanelViewModel(
            cache = get(activeSession),
            updateWorkSessionUseCase = get(updateSession),
            logoutUseCase = get(logout)
        )
    }
    viewModel { InventoryViewModel(get(inventoryMediator)) }
    viewModel {
        JobDetailViewModel(
            getJobByIdUseCase = get(jobByID), sessionCache = get(activeSession)
        )
    }
    viewModel {
        JobsViewModel(
            jobsRepository = get(jobMediator), sessionCache = get(activeSession)
        )
    }

    viewModel {
        AddJobViewModel(
            jobsRepository = get(jobMediator)
        )
    }
}
