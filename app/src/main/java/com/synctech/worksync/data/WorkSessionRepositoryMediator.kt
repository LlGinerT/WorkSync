package com.synctech.worksync.data


import android.util.Log
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

class WorkSessionRepositoryMediator(
    private val remote: WorkSessionRepository,
    private val cache: WorkSessionRepository
) : WorkSessionRepository {

    private var isCacheValid = false

    override suspend fun saveWorkSession(session: WorkSessionDomainModel) {
        remote.saveWorkSession(session)           // guarda en remoto
        cache.saveWorkSession(session)            // guarda en caché también
        isCacheValid = true
    }

    override suspend fun getWorkSession(userId: String): List<WorkSessionDomainModel> {
        return if (isCacheValid) {
            cache.getWorkSession(userId)
        } else {
            val sessions = remote.getWorkSession(userId)
            sessions.forEach { cache.saveWorkSession(it) }
            isCacheValid = true
            Log.i("Mediator", "Sesiones obtenidas del mock y cacheadas")
            sessions
        }
    }

    override suspend fun updateWorkSession(session: WorkSessionDomainModel): Boolean {
        val updatedRemote = remote.updateWorkSession(session)
        val updatedCache = cache.updateWorkSession(session)
        return updatedRemote && updatedCache
    }

    fun invalidateCache() {
        isCacheValid = false
    }
}
