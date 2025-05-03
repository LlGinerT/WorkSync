package com.synctech.worksync.domain.useCases.session

import android.util.Log
import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.domain.exceptions.AuthError
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository
import com.synctech.worksync.domain.repositories.UserAuthRepository
import com.synctech.worksync.domain.repositories.WorkSessionRepository
import java.util.UUID

/**
 * Caso de uso para autenticar credenciales y preparar el contexto de sesión activa.
 *
 * Este caso de uso:
 * - Valida email y contraseña.
 * - Recupera el modelo de empleado.
 * - Inicia o restaura sesión de trabajo.
 * - Guarda usuario y sesión en caché para su uso en otras pantallas.
 *
 * @property userAuthRepository Repositorio que autentica al usuario.
 * @property employeesRepository Repositorio que proporciona datos del empleado.
 * @property sessionRepository Repositorio de sesiones que gestiona la creación y restauración.
 * @property sessionCache Caché en memoria donde se almacena el contexto activo.
 */
class AuthUserUseCase(
    private val userAuthRepository: UserAuthRepository,
    private val employeesRepository: EmployeesRepository,
    private val sessionRepository: WorkSessionRepository,
    private val sessionCache: CacheActiveSessionRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<EmployeeDomainModel> {
        return try {
            val userId = userAuthRepository.authUser(email, password)
                ?: return Result.failure(AuthError.InvalidCredentials)

            val employee = employeesRepository.getEmployee(userId)
                ?: return Result.failure(AuthError.UserNotFound)

            sessionCache.setUser(employee)

            // Intenta restaurar sesión activa
            val activeSession = sessionRepository
                .getWorkSession(userId)
                .filter { it.endTime == null }
                .maxByOrNull { it.startTime }

            if (activeSession != null) {
                Log.i("AuthUseCase", "Sesión restaurada desde repositorio")
                sessionCache.setSession(activeSession)
            } else {
                // Inicia una nueva
                val newSession = WorkSessionDomainModel(
                    sessionId = UUID.randomUUID().toString(),
                    userId = userId,
                    startTime = System.currentTimeMillis(),
                    endTime = null,
                    durationInSeconds = null
                )
                sessionRepository.saveWorkSession(newSession)
                sessionCache.setSession(newSession)
                Log.i("AuthUseCase", "Sesión iniciada y cacheada")
            }

            Result.success(employee)
        } catch (e: Exception) {
            Log.e("AuthUseCase", "Error inesperado", e)
            Result.failure(AuthError.Unknown(e))
        }
    }
}

