package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.WorkSessionDomainModel

data class WorkSessionUIModel(
    val startTime: Long = 0,
)

fun WorkSessionDomainModel.toUi() = WorkSessionUIModel(startTime = startTime)
