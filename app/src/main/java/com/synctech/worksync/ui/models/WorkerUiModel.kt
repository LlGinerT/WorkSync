package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.WorkerDomainModel

data class WorkerUiModel(
    val name: String,
    val category: String
)

fun WorkerDomainModel.toUi() = WorkerUiModel(name = name, category = if(isAdmin)"Encargado" else "Tecnico")