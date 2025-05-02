package com.synctech.worksync.di

import com.synctech.worksync.data.EmployeesMediator
import com.synctech.worksync.data.cache.CacheEmployeesRepository
import com.synctech.worksync.data.testData.MockEmployeesRepository
import com.synctech.worksync.di.Qualifiers.empCache
import com.synctech.worksync.di.Qualifiers.empMediator
import com.synctech.worksync.di.Qualifiers.empMock
import com.synctech.worksync.domain.repositories.EmployeesRepository
import org.koin.dsl.module

val dataModule = module {

    // Repositorios
    single<EmployeesRepository>(empMock) { MockEmployeesRepository() }
    single<EmployeesRepository>(empCache) { CacheEmployeesRepository() }

    // Mediador
    single<EmployeesRepository>(empMediator) {
        EmployeesMediator(
            remote = get(empMock),
            cache = get(empCache)
        )
    }
}
