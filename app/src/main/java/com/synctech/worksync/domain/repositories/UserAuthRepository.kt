package com.synctech.worksync.domain.repositories


/**
 * Interfaz para autenticación de usuarios.
 */
interface UserAuthRepository {
    /**
     * Autentica al usuario con las credenciales proporcionadas.
     *
     * @param email Correo electrónico.
     * @param password Contraseña.
     * @return El ID del usuario si la autenticación es correcta, o `null` si falla.
     */
    fun authUser(email: String, password: String): String?

    /**
     * Busca un usuario por email y devuelve su userId si existe.
     *
     * @param email Correo electrónico.
     * @return El ID del usuario si existe, o `null` si no está registrado.
     */
    fun findUserIdByEmail(email: String): String?
}
