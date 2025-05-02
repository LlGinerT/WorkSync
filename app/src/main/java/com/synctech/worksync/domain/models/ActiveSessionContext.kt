package com.synctech.worksync.domain.models

data class ActiveSessionContext(
    val user: EmployeeDomainModel?,
    val session: WorkSessionDomainModel?,
)