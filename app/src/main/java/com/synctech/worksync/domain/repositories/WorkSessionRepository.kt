package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.domainModels.WorkSessionDomainModel

interface WorkSessionRepository {
   suspend fun saveWorkSession(session: WorkSessionDomainModel)
   suspend fun getWorkSessionsByUser(userId: String): List<WorkSessionDomainModel>
}