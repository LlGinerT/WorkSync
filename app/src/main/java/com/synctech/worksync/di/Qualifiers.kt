package com.synctech.worksync.di

import org.koin.core.qualifier.named

object Qualifiers {
    // WorkSessionRepository
    val workSessionMock = named("wsMock")
    val workSessionMediator = named("wsMediator")

    // InventoryRepository
    val inventoryMock = named("invMock")
    val inventoryCache = named("invCache")
    val inventoryMediator = named("invMediator")

    // JobsRepository
    val jobMock = named("jobMock")
    val jobCache = named("jobCache")
    val jobMediator = named("jobMediator")

    // UserAuthRepository
    val userAuthMock = named("uaMock")

    // CacheActiveSessionRepository
    val activeSession = named("activeSessionCache")

    // EmployeesRepository
    val employeeMock = named("empMock")
    val employeeCache = named("empCache")
    val employeeMediator = named("empMediator")

    // UseCases
    val updateSession = named("updateSession")
    val authUser = named("authUser")
    val logout = named("logout")
    val jobByID = named("jobByID")
}
