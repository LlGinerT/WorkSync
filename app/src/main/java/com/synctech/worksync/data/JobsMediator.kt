package com.synctech.worksync.data

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository

/**
 * Mediador que coordina entre el repositorio remoto (mock) y la caché local de trabajos.
 *
 * En esta etapa, el repositorio remoto simula Firebase y la caché se utiliza para minimizar lecturas.
 *
 * @property cache Repositorio en caché.
 * @property remote Repositorio mock que simula el backend remoto.
 */
class JobsMediator(
    private val cache: JobsRepository,
    private val remote: JobsRepository
) : JobsRepository {

    override suspend fun getJobs(user: EmployeeDomainModel): List<JobDomainModel> {
        val cached = cache.getJobs(user)
        return if (cached.isNotEmpty()) {
            cached
        } else {
            val remote = remote.getJobs(user)
            remote.forEach { cache.addJob(it) }
            remote
        }
    }

    override suspend fun getJobById(jobId: String): JobDomainModel? {
        return cache.getJobById(jobId) ?: remote.getJobById(jobId)
            ?.also {
                cache.addJob(it)
            }
    }

    // Métodos futuros no implementados
    override suspend fun addJob(jobDomainModel: JobDomainModel): Boolean {
        return false
    }

    override suspend fun updateJob(jobDomainModel: JobDomainModel): Boolean {
        return false
    }

    override suspend fun removeJob(jobDomainModel: JobDomainModel): Boolean {
        return false
    }
}
