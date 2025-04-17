package com.synctech.worksync.ui.screens.detailPanel

import com.synctech.worksync.ui.models.JobUiModel

data class JobDetailState(
    val job: JobUiModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
