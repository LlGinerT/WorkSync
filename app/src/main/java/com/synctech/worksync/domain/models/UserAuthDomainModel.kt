package com.synctech.worksync.domain.models

data class UserAuthDomainModel(
    val userId: String,
    val email: String,
    val password: String
)
