package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.repository.WorkRepository
import com.synctech.worksync.domain.models.Work

class GetWorkUseCase(
    private val workRepository: WorkRepository
) {
    operator fun invoke(): List<Work> {
        return workRepository.getWork()
    }
}
