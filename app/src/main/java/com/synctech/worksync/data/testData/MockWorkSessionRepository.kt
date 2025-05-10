package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

class MockWorkSessionRepository : WorkSessionRepository {
    private val sessionList = mutableListOf<WorkSessionDomainModel>()

    override suspend fun saveWorkSession(session: WorkSessionDomainModel) {
        sessionList.add(session)
    }

    override suspend fun getWorkSession(userId: String): List<WorkSessionDomainModel> {
        return sessionList.filter { it.userId == userId }
    }

    override suspend fun updateWorkSession(session: WorkSessionDomainModel): Boolean {
        val index = sessionList.indexOfFirst { it.sessionId == session.sessionId }
        return if (index != -1) {
            sessionList[index] = session
            true
        } else {
            false
        }
    }
}