package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.exceptions.AuthError
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository
import com.synctech.worksync.domain.repositories.UserAuthRepository

/**
 * Caso de uso que autentica al usuario y recupera su perfil si las credenciales son válidas.
 *
 * Este caso de uso:
 * - Valida el email y contraseña en el repositorio de autenticación
 * - Recupera el modelo del trabajador desde el repositorio de empleados
 * - Devuelve un [Result] con el trabajador o un error de tipo [AuthError]
 *
 * @property userAuthRepository Repositorio que gestiona la autenticación de credenciales.
 * @property employeesRepository Repositorio que contiene los datos de los trabajadores.
 */
class AuthUserUseCase(
    private val userAuthRepository: UserAuthRepository,
    private val employeesRepository: EmployeesRepository
) {

    /**
     * Ejecuta la autenticación.
     *
     * @param email Correo del usuario.
     * @param password Contraseña del usuario.
     * @return [Result.success] con el empleado si las credenciales son correctas,
     *         o [Result.failure] con [AuthError] si algo falla.
     */
    suspend operator fun invoke(email: String, password: String): Result<EmployeeDomainModel> {
        return try {
            val userId = userAuthRepository.authUser(email, password) ?: return Result.failure(
                AuthError.InvalidCredentials
            )

            val employee = employeesRepository.getWorker(userId)
                ?: return Result.failure(AuthError.UserNotFound)

            Result.success(employee)
        } catch (e: Exception) {
            Result.failure(AuthError.Unknown(e))
        }
    }
}
