package com.synctech.worksync.di

import com.synctech.worksync.ui.screens.loginPanel.LoginViewModel
import com.synctech.worksync.ui.screens.userPanel.UserPanelViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(named("AuthUser"))) }
    viewModel {
        UserPanelViewModel(
            cache = get(named("cache")),
            updateWorkSessionUseCase = get(named("UpdateWorkSession")),
            getActiveSessionUseCase = get(named("GetActiveSession"))
        )
    }
}
