package com.synctech.worksync.domain.useCases

import android.util.Log
import com.synctech.worksync.data.cache.CacheUserSessionRepository
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
    private val employeesRepository: EmployeesRepository,
    private val restoreWorkSessionUseCase: RestoreWorkSessionUseCase,
    private val startWorkSessionUseCase: StartWorkSessionUseCase,
    private val sessionCache: CacheUserSessionRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<EmployeeDomainModel> {
        return try {
            val userId = userAuthRepository.authUser(email, password)
                ?: return Result.failure(AuthError.InvalidCredentials)

            val employee = employeesRepository.getEmployee(userId)
                ?: return Result.failure(AuthError.UserNotFound)

            sessionCache.setUser(employee) // guardamos el usuario actual

            val restoredSessionResult = restoreWorkSessionUseCase(userId)

            restoredSessionResult.fold(
                onSuccess = { session ->
                    if (session != null) {
                        sessionCache.setSession(session) // también cacheamos la sesión restaurada
                        Log.i("AuthUseCase", "Sesión restaurada desde Firebase y cacheada")
                    } else {
                        val start = System.currentTimeMillis()
                        startWorkSessionUseCase(userId, start)
                        // el Mediator ya cacheará esta sesión automáticamente
                        Log.i("AuthUseCase", "Nueva sesión iniciada y cacheada")
                    }

                    Result.success(employee)
                },
                onFailure = { Result.failure(AuthError.Unknown(it)) }
            )
        } catch (e: Exception) {
            Result.failure(AuthError.Unknown(e))
        }
    }
}
