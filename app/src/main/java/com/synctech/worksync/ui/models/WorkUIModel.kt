package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.models.Works

/**
 * Representa un modelo de datos para la UI que contiene el título y una lista de trabajos.
 *
 * @param title El título de la lista de trabajos.
 * @param works Una lista de objetos [WorkUIModel] que contienen la información de los trabajos.
 */
data class WorksUIModel(
    val title: String,
    val works: List<WorkUIModel>
)

/**
 * Representa un modelo de datos para la UI que contiene información sobre un trabajo individual.
 *
 * @param jobName El nombre del trabajo a realizar.
 * @param clientName El nombre del cliente que solicita el trabajo.
 * @param description La descripción del trabajo.
 * @param address La dirección donde se debe realizar el trabajo.
 */
data class WorkUIModel(
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String,
    val assignedTo: String?
)

/**
 * Convierte un objeto [Works] del dominio a un modelo de datos para la UI [WorksUIModel].
 *
 * @return Un objeto [WorksUIModel] que contiene el título y una lista de trabajos convertidos a la UI.
 */
fun Works.toUI() = WorksUIModel(
    title = this.title,
    works = this.works.map { it.toUI() }
)

/**
 * Convierte un objeto [Work] del dominio a un modelo de datos para la UI [WorkUIModel].
 *
 * @return Un objeto [WorkUIModel] que contiene la información del trabajo en formato adecuado para la UI.
 */
fun Work.toUI() = WorkUIModel(
    jobName = this.jobName,
    clientName = this.clientName,
    description = this.description,
    address = this.address,
    assignedTo = this.assignedTo // Ahora incluimos el usuario asignado
)
