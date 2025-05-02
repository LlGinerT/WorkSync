package com.synctech.worksync.di

import com.synctech.worksync.data.WorkSessionMediator
import com.synctech.worksync.data.cache.CacheUserSessionRepository
import com.synctech.worksync.data.testData.MockUserAuthRepository
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.domain.repositories.UserAuthRepository
import com.synctech.worksync.domain.repositories.WorkSessionRepository
import com.synctech.worksync.domain.useCases.AuthUserUseCase
import com.synctech.worksync.domain.useCases.GetActiveSessionUseCase
import com.synctech.worksync.domain.useCases.RestoreWorkSessionUseCase
import com.synctech.worksync.domain.useCases.StartWorkSessionUseCase
import com.synctech.worksync.domain.useCases.UpdateWorkSessionUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sessionModule = module {

    // Cache de sesión activa
    single<CacheUserSessionRepository>(named("cache")) { CacheUserSessionRepository() }

    // Repos
    single<WorkSessionRepository>(named("mock")) { MockWorkSessionRepository() }
    single<UserAuthRepository>(named("mock")) { MockUserAuthRepository() }

    // Mediator
    single<WorkSessionRepository>(named("mediator")) {
        WorkSessionMediator(
            remote = get(named("mock")), sessionCache = get(named("cache"))
        )
    }

    // UseCases
    factory<StartWorkSessionUseCase>(named("StartWorkSession")) {
        StartWorkSessionUseCase(
            get(
                named(
                    "mediator"
                )
            )
        )
    }
    factory<UpdateWorkSessionUseCase>(named("UpdateWorkSession")) {
        UpdateWorkSessionUseCase(
            get(
                named("mediator")
            )
        )
    }
    factory<RestoreWorkSessionUseCase>(named("RestoreWorkSession")) {
        RestoreWorkSessionUseCase(
            get(
                named("mediator")
            )
        )
    }
    factory<GetActiveSessionUseCase>(named("GetActiveSession")) {
        GetActiveSessionUseCase(
            get(
                named(
                    "cache"
                )
            )
        )
    }
    factory<AuthUserUseCase>(named("AuthUser")) {
        AuthUserUseCase(
            userAuthRepository = get(named("mock")),
            employeesRepository = get(named("mediator")),
            restoreWorkSessionUseCase = get(named("RestoreWorkSession")),
            startWorkSessionUseCase = get(named("StartWorkSession")),
            sessionCache = get(named("cache"))
        )
    }
}

