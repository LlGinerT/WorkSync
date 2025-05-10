package com.synctech.worksync.domain.useCases.session

import android.util.Log
import com.synctech.worksync.domain.exceptions.SessionError
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

/**
 * Caso de uso para finalizar y guardar una sesión de trabajo que ya ha sido iniciada.
 *
 * Este caso de uso se ejecuta normalmente al cerrar sesión, y se encarga de:
 * - Calcular la duración de la sesión en segundos
 * - Actualizar una sesión ya existente en el repositorio
 *
 * @property repository Repositorio que gestiona las sesiones de trabajo.
 */
class UpdateWorkSessionUseCase(
    private val repository: WorkSessionRepository
) {

    /**
     * Ejecuta la actualización de una sesión de trabajo.
     *
     * @param sessionId ID de la sesión iniciada anteriormente.
     * @param userId ID del trabajador al que pertenece la sesión.
     * @param sessionStart Marca de tiempo del inicio (en milisegundos).
     * @param sessionEnd Marca de tiempo del fin (en milisegundos).
     * @return [Result.success] si se actualizó correctamente, o [Result.failure] con [SessionError] si hubo un problema.
     */
    suspend operator fun invoke(
        sessionId: String,
        userId: String,
        sessionStart: Long,
        sessionEnd: Long
    ): Result<Unit> {
        return try {
            val duration = (sessionEnd - sessionStart) / 1000

            val session = WorkSessionDomainModel(
                sessionId = sessionId,
                userId = userId,
                startTime = sessionStart,
                endTime = sessionEnd,
                durationInSeconds = duration
            )

            val updated = repository.updateWorkSession(session)

            if (updated) {
                Log.i(
                    "UpdateWorkSessionUseCase",
                    "Sesión finalizada para $userId con duración: $duration segundos"
                )
                Result.success(Unit)
            } else {
                Log.w("UpdateWorkSessionUseCase", "No se encontró la sesión para actualizar")
                Result.failure(SessionError.SaveFailed)
            }

        } catch (e: Exception) {
            Log.e("UpdateWorkSessionUseCase", "Error técnico al actualizar sesión", e)
            Result.failure(SessionError.SaveFailed)
        }
    }
}
