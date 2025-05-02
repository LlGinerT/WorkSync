package com.synctech.worksync.domain.useCases.session

import android.util.Log
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

/**
 * Caso de uso para restaurar una sesión de trabajo activa no finalizada.
 *
 * Se utiliza normalmente al abrir la aplicación para comprobar si el usuario
 * tenía una sesión en curso (sin `endTime`) y continuarla.
 *
 * @property repository Repositorio que gestiona las sesiones de trabajo.
 */
class RestoreWorkSessionUseCase(
    private val repository: WorkSessionRepository
) {

    /**
     * Intenta recuperar una sesión activa sin finalizar.
     *
     * @param userId ID del usuario del que se buscan sesiones.
     * @return [Result.success] con la sesión activa (si existe), o [Result.success(null)] si no hay sesión abierta.
     *         Devuelve [Result.failure] si ocurre un error técnico.
     */
    suspend operator fun invoke(userId: String): Result<WorkSessionDomainModel?> {
        return try {
            val sessions = repository.getWorkSession(userId)

            //TODO: Esta logica debera ir en el repositorio de firebase para optimizar consultas
            // Cuando se use Firebase:
            // Mover el filtrado a `WorkSessionRepository.getActiveSessionForUser(userId)`
            // usando .whereEqualTo("endTime", null)
            val lastSession = sessions
                .filter { it.endTime == null }
                .maxByOrNull { it.startTime }

            if (lastSession != null) {
                Log.i("RestoreSession", "Sesión restaurada con ID: ${lastSession.sessionId}")
            } else {
                Log.i("RestoreSession", "No hay sesiones activas para $userId")
            }

            Result.success(lastSession)
            //TODO :Aplicar en tener firebase
//        } catch (e: FirebaseException) {
//            Log.e("RestoreSession", "Error inesperado al restaurar sesión", e)
//            Result.failure(SessionError.RestoreSessionFailed)
        } catch (e: Exception) {
            Log.e("RestoreSession", "Error inesperado al restaurar sesión", e)
            Result.failure(e)
        }
    }
}
