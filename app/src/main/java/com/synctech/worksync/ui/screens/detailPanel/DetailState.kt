package com.synctech.worksync.ui.screens.detailPanel

import com.synctech.worksync.ui.models.JobUIModel

data class DetailState(
    val work: JobUIModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
