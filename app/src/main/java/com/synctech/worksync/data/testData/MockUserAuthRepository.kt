package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.domainModels.MockUserModel
import com.synctech.worksync.domain.repositories.UserAuthRepository

/**
 * Implementación simulada de UserAuthRepository para pruebas locales.
 */
class MockUserAuthRepository : UserAuthRepository {

    private val mockUsers = listOf(
        Triple("1", "encargado@gmail.com", "1234"),
        Triple("2", "tecnico1@gmail.com", "1234"),
        Triple("3", "tecnico2@gmail.com", "1234")
    )

    override fun authUser(email: String, password: String): String? {
        return mockUsers.firstOrNull { it.second == email && it.third == password }?.first
    }

    override fun findUserIdByEmail(email: String): String? {
        return mockUsers.firstOrNull { it.second == email }?.first
    }
}