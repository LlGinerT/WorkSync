package com.synctech.worksync.domain.useCases.session

import android.util.Log
import com.synctech.worksync.domain.exceptions.SessionError
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository
import java.util.UUID

class StartWorkSessionUseCase(
    private val repository: WorkSessionRepository
) {
    suspend operator fun invoke(
        userId: String,
        sessionStart: Long
    ): Result<Unit> {
        return try {
            val session = WorkSessionDomainModel(
                sessionId = UUID.randomUUID().toString(),
                userId = userId,
                startTime = sessionStart,
                endTime = null,
                durationInSeconds = null
            )

            repository.saveWorkSession(session)

            Log.i("StartWorkSessionUseCase", "Simulación: sesión iniciada en Firebase")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("StartWorkSessionUseCase", "Error al iniciar sesión", e)
            Result.failure(SessionError.SaveFailed)
        }
    }
}