package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.models.Works
import com.synctech.worksync.domain.repository.WorkRepository

class GetWorkUseCase(
    private val workRepository: WorkRepository
) {

    operator fun invoke(): Works = workRepository.getWork()
}
