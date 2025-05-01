package com.synctech.worksync.di

import com.synctech.worksync.data.WorkSessionRepositoryMediator
import com.synctech.worksync.data.cache.CacheWorkSessionRepository
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.domain.repositories.WorkSessionRepository
import com.synctech.worksync.domain.useCases.RestoreWorkSessionUseCase
import com.synctech.worksync.domain.useCases.StartWorkSessionUseCase
import com.synctech.worksync.domain.useCases.UpdateWorkSessionUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sessionModule = module {

    // Repos individuales con nombres
    single<WorkSessionRepository>(named("mock")) { MockWorkSessionRepository() }
    single<WorkSessionRepository>(named("cache")) { CacheWorkSessionRepository() }

    // Repositorio combinado sin nombre (por defecto)
    single<WorkSessionRepository> {
        WorkSessionRepositoryMediator(
            remote = get(named("mock")),
            cache = get(named("cache"))
        )
    }

    single { get<WorkSessionRepository>() as WorkSessionRepositoryMediator }

    // UseCases que usan ese repositorio por defecto
    single { StartWorkSessionUseCase(get()) }
    single { UpdateWorkSessionUseCase(get()) }
    single { RestoreWorkSessionUseCase(get()) }

}