package com.synctech.worksync.domain.repository

import com.synctech.worksync.domain.models.Works

interface WorkRepository {
    fun getWork(): Works
}