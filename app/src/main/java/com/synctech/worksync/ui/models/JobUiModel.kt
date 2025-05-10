package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.JobDomainModel

/**
 * Representa un modelo de datos para la UI que contiene información sobre un trabajo individual.
 *
 * @param jobName El nombre del trabajo a realizar.
 * @param clientName El nombre del cliente que solicita el trabajo.
 * @param description La descripción del trabajo.
 * @param address La dirección donde se debe realizar el trabajo.
 */
data class JobUiModel(
    val jobId: String,
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String,
    val assignedTo: String?
)

/**
 * Convierte un objeto [JobDomainModel] del dominio a un modelo de datos para la UI [JobUiModel].
 *
 * @return Un objeto [JobUiModel] que contiene la información del trabajo en formato adecuado para la UI.
 */
fun JobDomainModel.toUi() = JobUiModel(
    jobId = this.jobId,
    jobName = this.jobName,
    clientName = this.clientName,
    description = this.description,
    address = this.address,
    assignedTo = this.assignedTo // Ahora incluimos el usuario asignado
)
