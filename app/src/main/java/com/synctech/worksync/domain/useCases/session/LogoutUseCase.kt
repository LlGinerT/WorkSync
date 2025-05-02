package com.synctech.worksync.domain.useCases.session

import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.domain.exceptions.SessionError


class LogoutUseCase(
    private val cache: CacheActiveSessionRepository,
    private val updateWorkSessionUseCase: UpdateWorkSessionUseCase
) {

    /**
     * Finaliza la sesión activa si hay una en curso y limpia la caché.
     *
     * @return [Result.success] si la sesión se cerró correctamente,
     * o [Result.failure] si no había sesión activa o hubo un error al guardar.
     */
    suspend operator fun invoke(): Result<Unit> {
        val user = cache.getUser()
        val session = cache.getSession()

        if (user == null || session == null) {
            return Result.failure(SessionError.IncompleteSession)
        }

        val result = updateWorkSessionUseCase(
            sessionId = session.sessionId,
            userId = user.userId,
            sessionStart = session.startTime,
            sessionEnd = System.currentTimeMillis()
        )

        if (result.isSuccess) {
            cache.clearAll()
        }

        return result
    }
}
