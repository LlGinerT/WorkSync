package com.synctech.worksync.data


import android.util.Log
import com.synctech.worksync.data.cache.CacheUserSessionRepository
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

class WorkSessionMediator(
    private val remote: WorkSessionRepository,
    private val sessionCache: CacheUserSessionRepository
) : WorkSessionRepository {

    override suspend fun saveWorkSession(session: WorkSessionDomainModel) {
        remote.saveWorkSession(session)
        Log.i("WorkSessionMediator", "Sesión guardada en remoto")
        sessionCache.setSession(session)
        Log.i("WorkSessionMediator", "Sesión guardada en cache")
    }

    override suspend fun getWorkSession(userId: String): List<WorkSessionDomainModel> {
        // Esto se usa solo al hacer login para recuperar sesiones no cerradas
        return remote.getWorkSession(userId)
    }

    override suspend fun updateWorkSession(session: WorkSessionDomainModel): Boolean {
        val result = remote.updateWorkSession(session)
        Log.i("WorkSessionMediator", "Sesión actualizada en remoto: $result")
        if (result) sessionCache.clearSession()
        return result
    }
}

