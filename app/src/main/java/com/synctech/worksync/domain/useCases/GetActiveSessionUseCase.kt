package com.synctech.worksync.domain.useCases

import com.synctech.worksync.data.cache.CacheUserSessionRepository
import com.synctech.worksync.domain.models.ActiveSessionContext

class GetActiveSessionUseCase(
    private val cache: CacheUserSessionRepository,
) {
    operator fun invoke(): ActiveSessionContext {
        return ActiveSessionContext(
            user = cache.getUser(),
            session = cache.getSession()
        )
    }
}