package com.synctech.worksync.ui.screens.detailPanel

import com.synctech.worksync.ui.models.DetailUIModel

data class DetailState(
    val work: DetailUIModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
