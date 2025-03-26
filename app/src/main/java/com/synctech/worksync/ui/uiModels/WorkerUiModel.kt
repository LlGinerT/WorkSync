package com.synctech.worksync.ui.uiModels

import com.synctech.worksync.domain.domainModels.WorkerDomainModel

data class WorkerUiModel(
    val name: String,
    val category: String
)

fun WorkerDomainModel.toUi() = WorkerUiModel(name = name, category = if(isAdmin)"Encargado" else "Tecnico")