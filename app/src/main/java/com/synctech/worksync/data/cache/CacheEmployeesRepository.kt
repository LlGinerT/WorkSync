package com.synctech.worksync.data.cache

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

class CacheEmployeesRepository : EmployeesRepository {

    private val cache: MutableList<EmployeeDomainModel> = mutableListOf()

    override fun getEmployee(userId: String): EmployeeDomainModel? {
        return cache.firstOrNull { it.userId == userId }
    }

    override fun getEmployeeList(): List<EmployeeDomainModel> {
        return cache.toList()
    }

    override fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean {
        cache.clear()
        cache.addAll(list)
        return true
    }
}