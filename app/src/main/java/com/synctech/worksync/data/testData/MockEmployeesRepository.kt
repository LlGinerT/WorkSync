package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

class MockEmployeesRepository : EmployeesRepository {

    private val workerList = listOf(
        EmployeeDomainModel("1", "Pepito Encargado Lopez", true),
        EmployeeDomainModel("2", "Laura Baeza Ruiz", false),
        EmployeeDomainModel("3", "Luis Giner Tendero", false)
    )

    override fun getWorker(userId: String): EmployeeDomainModel? {
        return workerList.firstOrNull { it.userId == userId }
    }

    override fun getWorkersList(): List<EmployeeDomainModel> {
        return workerList
    }
}