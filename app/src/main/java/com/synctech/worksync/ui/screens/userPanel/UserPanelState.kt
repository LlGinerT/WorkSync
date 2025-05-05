package com.synctech.worksync.ui.screens.userPanel

import com.synctech.worksync.ui.models.EmployeeUiModel
import com.synctech.worksync.ui.models.WorkSessionUIModel

data class UserPanelState(
    val employee: EmployeeUiModel? = null,
    val workSession: WorkSessionUIModel? = null,
    val secondsWorked: Int = 0,
    val isLoggingOut: Boolean = false
)