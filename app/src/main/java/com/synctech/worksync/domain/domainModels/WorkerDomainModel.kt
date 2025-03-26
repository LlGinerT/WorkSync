package com.synctech.worksync.domain.domainModels

/**
 * Modelo de dominio que representa un trabajador en el sistema.
 *
 * @property userId Identificador único del usuario.
 * @property name Nombre del trabajador.
 * @property isAdmin Indica si el usuario tiene permisos de administrador.
 */
data class WorkerDomainModel(
    val userId: String,
    val name: String,
    val isAdmin: Boolean,
)