package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.Detail
import com.synctech.worksync.domain.models.Work

interface DetailRepository {
    suspend fun getWorkById(workId: String): Detail?
}
