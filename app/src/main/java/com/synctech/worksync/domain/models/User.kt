package com.synctech.worksync.domain.models


/**
 * Representa un usuario con su rol.
 *
 * @param username El nombre de usuario.
 * @param isAdmin Indica si el usuario tiene rol de administrador.
 */
data class User(
    val username: String,
    val isAdmin: Boolean
)
