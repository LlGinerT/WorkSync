package com.synctech.worksync.di

import com.synctech.worksync.data.EmployeesMediator
import com.synctech.worksync.data.cache.CacheEmployeesRepository
import com.synctech.worksync.data.testData.MockEmployeesRepository
import com.synctech.worksync.domain.repositories.EmployeesRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    //Repos
    single<EmployeesRepository>(named("mock")) { MockEmployeesRepository() }
    single { CacheEmployeesRepository() }

    // Mediator
    single<EmployeesRepository> {
        EmployeesMediator(
            remote = get(named("mock")),
            cache = get()
        )
    }

    single { get<EmployeesRepository>() as EmployeesMediator }

}