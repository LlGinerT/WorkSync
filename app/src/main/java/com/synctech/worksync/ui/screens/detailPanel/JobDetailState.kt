package com.synctech.worksync.ui.screens.detailPanel

import com.synctech.worksync.ui.models.JobUiModel

data class JobDetailState(
    val job: JobUiModel? = null,
    val showLoadingIndicator: Boolean = false,
    val errorMessage: String? = null
)
