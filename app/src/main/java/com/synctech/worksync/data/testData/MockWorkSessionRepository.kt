package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.domainModels.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

class MockWorkSessionRepository : WorkSessionRepository {
    private val sessionList = mutableListOf<WorkSessionDomainModel>()

    override suspend fun saveWorkSession(session: WorkSessionDomainModel) {
        sessionList.add(session)
    }

    override suspend fun getWorkSessionsByUser(userId: String): List<WorkSessionDomainModel> {
        return sessionList.filter { it.userId == userId }
    }
}