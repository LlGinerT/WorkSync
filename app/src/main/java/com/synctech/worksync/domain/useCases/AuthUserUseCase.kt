package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository
import com.synctech.worksync.domain.repositories.UserAuthRepository

/**
 * Caso de uso que gestiona la autenticación de usuarios y recupera su información de perfil.
 *
 * @property userAuthRepository Repositorio de autenticación de usuarios.
 * @property employeesRepository Repositorio de datos de los trabajadores.
 */
class AuthUserUseCase(
    private val userAuthRepository: UserAuthRepository,
    private val employeesRepository: EmployeesRepository
) {
    /**
     * Ejecuta el caso de uso de autenticación.
     *
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     * @return El perfil del trabajador si la autenticación es exitosa, o `null` si falla.
     */
    operator fun invoke(email: String, password: String): EmployeeDomainModel? {
        val userId = userAuthRepository.authUser(email, password)
        return userId?.let { employeesRepository.getWorker(it) }
    }

    /**
     * Función auxiliar para comprobar que el email existe en el repository
     *
     * @param email Correo electrónico del usuario.
     * @return Bolean si existe o no.
     */
    fun checkIfEmailExists(email: String): Boolean {
        return userAuthRepository.findUserIdByEmail(email) != null
    }
}