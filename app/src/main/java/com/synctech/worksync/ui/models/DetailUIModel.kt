package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.Detail
import com.synctech.worksync.domain.models.Work

data class DetailUIModel(
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String,
    val assignedTo: String?
)

fun Detail.toUI() = DetailUIModel(
    jobName = this.jobName,
    clientName = this.clientName,
    description = this.description,
    address = this.address,
    assignedTo = this.assignedTo // Ahora incluimos el usuario asignado
)