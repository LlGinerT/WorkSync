package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.models.Detail
import com.synctech.worksync.domain.repositories.DetailRepository

class GetWorkByIdUseCase(
    private val detailRepository: DetailRepository
) {
    suspend operator fun invoke(workId: String): Detail? {
        return detailRepository.getWorkById(workId)
    }
}
