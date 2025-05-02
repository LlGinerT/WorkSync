package com.synctech.worksync.di

import com.synctech.worksync.data.EmployeesMediator
import com.synctech.worksync.data.cache.CacheEmployeesRepository
import com.synctech.worksync.data.cache.CacheInventoryRepository
import com.synctech.worksync.data.testData.MockEmployeesRepository
import com.synctech.worksync.data.testData.MockInventoryRepository
import com.synctech.worksync.di.Qualifiers.employeeCache
import com.synctech.worksync.di.Qualifiers.employeeMediator
import com.synctech.worksync.di.Qualifiers.employeeMock
import com.synctech.worksync.di.Qualifiers.inventoryCache
import com.synctech.worksync.di.Qualifiers.inventoryMediator
import com.synctech.worksync.di.Qualifiers.inventoryMock
import com.synctech.worksync.domain.repositories.EmployeesRepository
import com.synctech.worksync.domain.repositories.InventoryRepository
import org.koin.dsl.module

val dataModule = module {

    // Repositorios
    single<EmployeesRepository>(employeeMock) { MockEmployeesRepository() }
    single<EmployeesRepository>(employeeCache) { CacheEmployeesRepository() }
    single<InventoryRepository>(inventoryMock) { MockInventoryRepository() }
    single<InventoryRepository>(inventoryCache) { CacheInventoryRepository() }

    // Mediadores
    single<EmployeesRepository>(employeeMediator) {
        EmployeesMediator(
            remote = get(employeeMock),
            cache = get(employeeCache)
        )
    }
    single<InventoryRepository>(inventoryMediator) {
        com.synctech.worksync.data.InventoryMediator(
            remote = get(inventoryMock),
            cache = get(inventoryCache)
        )
    }
}
