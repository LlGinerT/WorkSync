package com.synctech.worksync.domain.models

data class Work(
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String
)

data class Works(
    val title: String,
    val work: List<Work>
)
