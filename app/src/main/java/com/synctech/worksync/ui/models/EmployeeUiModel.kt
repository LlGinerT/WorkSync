package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.EmployeeDomainModel

data class EmployeeUiModel(
    val name: String,
    val category: String
)

fun EmployeeDomainModel.toUi() =
    EmployeeUiModel(name = name, category = if (isAdmin) "Encargado" else "Tecnico")