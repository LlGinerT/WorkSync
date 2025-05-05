package com.synctech.worksync.di

import com.synctech.worksync.data.EmployeesMediator
import com.synctech.worksync.data.InventoryMediator
import com.synctech.worksync.data.JobsMediator
import com.synctech.worksync.data.cache.CacheEmployeesRepository
import com.synctech.worksync.data.cache.CacheInventoryRepository
import com.synctech.worksync.data.cache.CacheJobsRepository
import com.synctech.worksync.data.testData.MockEmployeesRepository
import com.synctech.worksync.data.testData.MockInventoryRepository
import com.synctech.worksync.data.testData.MockJobsDataRepository
import com.synctech.worksync.di.Qualifiers.employeeCache
import com.synctech.worksync.di.Qualifiers.employeeMediator
import com.synctech.worksync.di.Qualifiers.employeeMock
import com.synctech.worksync.di.Qualifiers.inventoryCache
import com.synctech.worksync.di.Qualifiers.inventoryMediator
import com.synctech.worksync.di.Qualifiers.inventoryMock
import com.synctech.worksync.di.Qualifiers.jobByID
import com.synctech.worksync.di.Qualifiers.jobCache
import com.synctech.worksync.di.Qualifiers.jobMediator
import com.synctech.worksync.di.Qualifiers.jobMock
import com.synctech.worksync.domain.repositories.EmployeesRepository
import com.synctech.worksync.domain.repositories.InventoryRepository
import com.synctech.worksync.domain.repositories.JobsRepository
import com.synctech.worksync.domain.useCases.jobs.GetJobByIdUseCase
import org.koin.dsl.module

val dataModule = module {

    // Repositorios
    single<EmployeesRepository>(employeeMock) { MockEmployeesRepository() }
    single<EmployeesRepository>(employeeCache) { CacheEmployeesRepository() }
    single<InventoryRepository>(inventoryMock) { MockInventoryRepository() }
    single<InventoryRepository>(inventoryCache) { CacheInventoryRepository() }
    single<JobsRepository>(jobMock) { MockJobsDataRepository() }
    single<JobsRepository>(jobCache) { CacheJobsRepository() }

    // Mediadores
    single<EmployeesRepository>(employeeMediator) {
        EmployeesMediator(
            remote = get(employeeMock), cache = get(employeeCache)
        )
    }
    single<InventoryRepository>(inventoryMediator) {
        InventoryMediator(
            remote = get(inventoryMock), cache = get(inventoryCache)
        )
    }
    single<JobsRepository>(jobMediator) {
        JobsMediator(
            remote = get(jobMock), cache = get(jobCache)
        )
    }

    // Casos de uso
    factory<GetJobByIdUseCase>(jobByID) { GetJobByIdUseCase(get(jobMediator)) }
}
