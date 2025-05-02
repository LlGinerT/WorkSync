package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

class MockEmployeesRepository : EmployeesRepository {

    private val workerList = mutableListOf(
        EmployeeDomainModel("1", "Pepito Encargado Lopez", true),
        EmployeeDomainModel("2", "Laura Baeza Ruiz", false),
        EmployeeDomainModel("3", "Luis Giner Tendero", false)
    )

    override fun getEmployee(userId: String): EmployeeDomainModel? {
        return workerList.firstOrNull { it.userId == userId }
    }

    override fun getEmployeeList(): List<EmployeeDomainModel> {
        return workerList.toList()
    }

    override fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean {
        workerList.clear()
        workerList.addAll(list)
        return true
    }
}