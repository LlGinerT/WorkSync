package com.synctech.worksync.ui.session

import com.synctech.worksync.domain.domainModels.WorkerDomainModel
import com.synctech.worksync.ui.uiModels.WorkerUiModel

data class SessionState(
    val domainWorker: WorkerDomainModel? = null,
    val secondsWorked: Int = 0,
    val sessionStart: Long? = null,
    val sessionEnd: Long? = null
)
