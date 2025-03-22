package com.synctech.worksync.ui.viewmodel

import com.synctech.worksync.ui.models.WorkUIModel

data class WorkState(
    val showLoadingIndicator: Boolean = false,
    val works: List<WorkUIModel> = emptyList(),
    //val title: String = ""
)
