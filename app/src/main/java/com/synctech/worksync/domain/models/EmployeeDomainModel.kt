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
) {
    companion object {
        /**
         * Usuario especial del sistema utilizado para acciones automáticas o internas,
         * como sincronización de datos o procesos de mantenimiento.
         * Este usuario siempre tiene permisos administrativos.
         */
        val SYSTEM_USER = EmployeeDomainModel(
            userId = "system",
            name = "System",
            isAdmin = true
        )
    }
}