package com.synctech.worksync.domain.models

import com.synctech.worksync.ui.models.WorkUIModel


data class Works(
    val title: String,
    val works: List<Work>
)

data class Work(
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String
)