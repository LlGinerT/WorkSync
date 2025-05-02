package com.synctech.worksync.di

import org.koin.core.qualifier.named

object Qualifiers {
    // WorkSessionRepository
    val workSessionMock = named("wsMock")
    val workSessionMediator = named("wsMediator")

    // UserAuthRepository
    val userAuthMock = named("uaMock")

    // CacheActiveSessionRepository
    val activeSession = named("activeSessionCache")

    // EmployeesRepository
    val empMock = named("empMock")
    val empCache = named("empCache")
    val empMediator = named("empMediator")

    // UseCases
    val startSession = named("startSession")
    val updateSession = named("updateSession")
    val restoreSession = named("restoreSession")
    val authUser = named("authUser")
}
