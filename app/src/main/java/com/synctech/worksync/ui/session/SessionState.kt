package com.synctech.worksync.ui.session

import com.synctech.worksync.domain.models.WorkerDomainModel

data class SessionState(
    val domainWorker: WorkerDomainModel? = null,
    val secondsWorked: Int = 0,
    val sessionStart: Long? = null,
    val sessionEnd: Long? = null
)
