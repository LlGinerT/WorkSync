package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.EmployeeDomainModel

data class WorkerUiModel(
    val name: String,
    val category: String
)

fun EmployeeDomainModel.toUi() =
    WorkerUiModel(name = name, category = if (isAdmin) "Encargado" else "Tecnico")