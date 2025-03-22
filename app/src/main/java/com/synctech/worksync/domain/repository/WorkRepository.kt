package com.synctech.worksync.domain.repository

import com.synctech.worksync.domain.models.Work
import com.synctech.worksync.domain.models.Works

interface WorkRepository {
    fun getWork(): List<Work>
}