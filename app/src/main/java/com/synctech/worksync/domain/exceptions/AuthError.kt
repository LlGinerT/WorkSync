package com.synctech.worksync.domain.exceptions

/**
 * Representa errores específicos durante la autenticación de usuario.
 */
sealed class AuthError(message: String) : Exception(message) {

    data object InvalidCredentials : AuthError("Credenciales inválidas")
    data object UserNotFound : AuthError("Usuario no encontrado")
    data class Unknown(override val cause: Throwable) :
        AuthError("Error inesperado: ${cause.message}")
}
