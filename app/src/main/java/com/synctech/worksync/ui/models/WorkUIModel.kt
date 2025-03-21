package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.models.Works

data class WorksUIModel(
    val title: String,
    val works: List<WorkUIModel>
)

data class WorkUIModel(
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String
)

// Extensión para convertir de dominio a UI
fun Works.toUI() = WorksUIModel(
    title = this.title,
    works = this.work.map { it.toUI() }
)

fun Work.toUI() = WorkUIModel(
    jobName = this.jobName,
    clientName = this.clientName,
    description = this.description,
    address = this.address
)
