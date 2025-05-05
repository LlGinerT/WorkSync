package com.synctech.worksync.di

import com.synctech.worksync.data.WorkSessionMediator
import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.data.testData.MockUserAuthRepository
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.di.Qualifiers.activeSession
import com.synctech.worksync.di.Qualifiers.authUser
import com.synctech.worksync.di.Qualifiers.employeeMediator
import com.synctech.worksync.di.Qualifiers.logout
import com.synctech.worksync.di.Qualifiers.updateSession
import com.synctech.worksync.di.Qualifiers.userAuthMock
import com.synctech.worksync.di.Qualifiers.workSessionMediator
import com.synctech.worksync.di.Qualifiers.workSessionMock
import com.synctech.worksync.domain.repositories.UserAuthRepository
import com.synctech.worksync.domain.repositories.WorkSessionRepository
import com.synctech.worksync.domain.useCases.session.AuthUserUseCase
import com.synctech.worksync.domain.useCases.session.LogoutUseCase
import com.synctech.worksync.domain.useCases.session.UpdateWorkSessionUseCase
import org.koin.dsl.module

val sessionModule = module {

    // Cache de sesión activa
    single<CacheActiveSessionRepository>(activeSession) { CacheActiveSessionRepository() }

    // Repos
    single<WorkSessionRepository>(workSessionMock) { MockWorkSessionRepository() }
    single<UserAuthRepository>(userAuthMock) { MockUserAuthRepository() }

    // Mediator
    single<WorkSessionRepository>(workSessionMediator) {
        WorkSessionMediator(
            remote = get(workSessionMock), sessionCache = get(activeSession)
        )
    }

    // UseCases
    factory<UpdateWorkSessionUseCase>(updateSession) {
        UpdateWorkSessionUseCase(
            get(workSessionMediator)
        )
    }
    factory<LogoutUseCase>(logout) {
        LogoutUseCase(
            cache = get(activeSession),
            updateWorkSessionUseCase = get(updateSession)
        )
    }
    factory<AuthUserUseCase>(authUser) {
        AuthUserUseCase(
            userAuthRepository = get(userAuthMock),
            employeesRepository = get(employeeMediator),
            sessionRepository = get(workSessionMediator),
            sessionCache = get(activeSession)
        )
    }
}

