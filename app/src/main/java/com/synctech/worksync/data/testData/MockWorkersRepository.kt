package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.domainModels.WorkerDomainModel
import com.synctech.worksync.domain.repositories.WorkersRepository

class MockWorkersRepository : WorkersRepository {

    private val workerList = listOf(
        WorkerDomainModel("1", "Pepito Encargado Lopez", true),
        WorkerDomainModel("2", "Laura Baeza Ruiz", false),
        WorkerDomainModel("3", "Luis Giner Tendero", false)
    )

    override fun getWorker(userId: String): WorkerDomainModel? {
        return workerList.firstOrNull { it.userId == userId }
    }

    override fun getWorkersList(): List<WorkerDomainModel> {
        return workerList
    }
}