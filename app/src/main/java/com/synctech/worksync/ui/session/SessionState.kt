package com.synctech.worksync.ui.session

import com.synctech.worksync.domain.models.EmployeeDomainModel

data class SessionState(
    val employee: EmployeeDomainModel? = null,
    val secondsWorked: Int = 0,
    val sessionStart: Long? = null,
    val sessionEnd: Long? = null
)
