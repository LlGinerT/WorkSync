package com.synctech.worksync.domain.models

/**
 * Modelo de dominio que representa un trabajador en el sistema.
 *
 * @property userId Identificador único del usuario.
 * @property name Nombre del trabajador.
 * @property isAdmin Indica si el usuario tiene permisos de administrador.
 */
data class EmployeeDomainModel(
    val userId: String,
    val name: String,
    val isAdmin: Boolean,
)