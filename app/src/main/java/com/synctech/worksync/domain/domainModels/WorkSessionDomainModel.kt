package com.synctech.worksync.domain.domainModels


data class WorkSessionDomainModel(
    val sessionId: String,
    val userId: String,
    val startTime: Long?,
    val endTime: Long?,
    val durationInSeconds: Long,
)

