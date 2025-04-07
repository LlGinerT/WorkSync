package com.synctech.worksync.domain.models

import com.synctech.worksync.ui.models.WorkUIModel

/**
 * Representa una lista de trabajos con un título.
 *
 * @param title El título de la lista de trabajos.
 * @param works La lista de trabajos que contiene el objeto Works.
 */
data class Works(
    val title: String,
    val works: List<Work>
)

/**
 * Representa un trabajo con sus detalles.
 *
 * @param jobName El nombre del trabajo realizado.
 * @param clientName El nombre del cliente para quien se realiza el trabajo.
 * @param description Una descripción del trabajo que se va a realizar.
 * @param address La dirección donde se lleva a cabo el trabajo.
 * @param assignedTo Id del trabajador
 */
data class Work(
    // Te falta añadir un WorkID
    val workId: String,
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String,
    var assignedTo: String? = null
)
