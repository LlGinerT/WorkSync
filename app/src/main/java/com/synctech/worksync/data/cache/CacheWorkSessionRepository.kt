package com.synctech.worksync.data.cache

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.WorkSessionDomainModel

class CacheUserSessionRepository {

    private var currentUser: EmployeeDomainModel? = null
    private var activeSession: WorkSessionDomainModel? = null

    fun setUser(user: EmployeeDomainModel) {
        currentUser = user
    }

    fun getUser(): EmployeeDomainModel? = currentUser

    fun clearUser() {
        currentUser = null
    }

    fun setSession(session: WorkSessionDomainModel) {
        activeSession = session
    }

    fun getSession(): WorkSessionDomainModel? = activeSession

    fun clearSession() {
        activeSession = null
    }

    fun clearAll() {
        clearUser()
        clearSession()
    }
}


