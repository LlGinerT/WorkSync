package com.synctech.worksync.di

import com.synctech.worksync.data.WorkSessionMediator
import com.synctech.worksync.data.cache.CacheUserSessionRepository
import com.synctech.worksync.data.testData.MockUserAuthRepository
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.domain.repositories.UserAuthRepository
import com.synctech.worksync.domain.repositories.WorkSessionRepository
import com.synctech.worksync.domain.useCases.AuthUserUseCase
import com.synctech.worksync.domain.useCases.RestoreWorkSessionUseCase
import com.synctech.worksync.domain.useCases.StartWorkSessionUseCase
import com.synctech.worksync.domain.useCases.UpdateWorkSessionUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sessionModule = module {

    // Repos
    single<WorkSessionRepository>(named("mock")) { MockWorkSessionRepository() }
    single { CacheUserSessionRepository() }
    single<UserAuthRepository> { MockUserAuthRepository() }

    // Mediators
    single<WorkSessionRepository> {
        WorkSessionMediator(
            remote = get(named("mock")),
            sessionCache = get()
        )
    }

    single { get<WorkSessionRepository>() as WorkSessionMediator }

    // UseCases
    single { StartWorkSessionUseCase(get()) }
    single { UpdateWorkSessionUseCase(get()) }
    single { RestoreWorkSessionUseCase(get()) }
    single {
        AuthUserUseCase(
            userAuthRepository = get(),
            employeesRepository = get(),
            restoreWorkSessionUseCase = get(),
            startWorkSessionUseCase = get(),
            sessionCache = get()
        )
    }
}
