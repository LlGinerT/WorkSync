package com.synctech.worksync.di

import com.synctech.worksync.data.EmployeesMediator
import com.synctech.worksync.data.cache.CacheEmployeesRepository
import com.synctech.worksync.data.testData.MockEmployeesRepository
import com.synctech.worksync.domain.repositories.EmployeesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    // Repositorios
    single<EmployeesRepository>(named("mock")) { MockEmployeesRepository() }
    single<EmployeesRepository>(named("cache")) { CacheEmployeesRepository() }

    // Mediador
    single<EmployeesRepository>(named("mediator")) {
        EmployeesMediator(
            remote = get(named("mock")),
            cache = get(named("cache"))
        )
    }
}
