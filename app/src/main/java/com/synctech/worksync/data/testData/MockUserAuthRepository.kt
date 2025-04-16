package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.UserDomainModel
import com.synctech.worksync.domain.repositories.UserAuthRepository

/**
 * Implementación simulada de UserAuthRepository para pruebas locales.
 */
class MockUserAuthRepository : UserAuthRepository {

    private val usersList = listOf(
        UserDomainModel("1", "encargado@gmail.com", "1234"),
        UserDomainModel("2", "tecnico1@gmail.com", "1234"),
        UserDomainModel("3", "tecnico2@gmail.com", "1234")
    )

    override fun authUser(email: String, password: String): String? {
        return usersList.firstOrNull { it.email == email && it.password == password }?.userId
    }

    override fun findUserIdByEmail(email: String): String? {
        return usersList.firstOrNull { it.email == email }?.userId
    }
}