package com.synctech.worksync.domain.models



/**
 * Representa un trabajo con sus detalles.
 *
 * @param jobName El nombre del trabajo realizado.
 * @param clientName El nombre del cliente para quien se realiza el trabajo.
 * @param description Una descripción del trabajo que se va a realizar.
 * @param address La dirección donde se lleva a cabo el trabajo.
 * @param assignedTo Id del trabajador
 */
data class JobDomainModel(
    val jobId: String,
    val jobName: String,
    val clientName: String,
    val description: String,
    val address: String,
    var assignedTo: String? = null
)
