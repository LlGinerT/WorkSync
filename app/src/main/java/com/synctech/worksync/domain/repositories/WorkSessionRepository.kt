package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.WorkSessionDomainModel

interface WorkSessionRepository {
    suspend fun saveWorkSession(session: WorkSessionDomainModel)
    suspend fun getWorkSession(userId: String): List<WorkSessionDomainModel>
    suspend fun updateWorkSession(session: WorkSessionDomainModel): Boolean

}