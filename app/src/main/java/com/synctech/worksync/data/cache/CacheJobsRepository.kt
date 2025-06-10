package com.synctech.worksync.data.cache

import android.util.Log
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository

/**
 * Repositorio en caché para almacenar temporalmente la lista de trabajos.
 * Utiliza una lista mutable en memoria.
 */
class CacheJobsRepository : JobsRepository {

    /** Lista de trabajos en caché simulada en memoria. */
    private val cachedJobs = mutableListOf<JobDomainModel>()

    override suspend fun getJobs(user: EmployeeDomainModel): List<JobDomainModel> {
        return if (user.isAdmin) {
            cachedJobs
        } else {
            cachedJobs.filter { it.assignedTo == user.userId }
        }
    }

    override suspend fun getJobById(jobId: String): JobDomainModel? {
        return cachedJobs.firstOrNull { it.jobId == jobId }
    }

    override suspend fun createJob(jobDomainModel: JobDomainModel): Boolean {
        cachedJobs.add(jobDomainModel)
        Log.d("CacheJobsRepository", "Lista en cache: $cachedJobs")
        return true
    }

    override suspend fun updateJob(jobDomainModel: JobDomainModel): Boolean {
        val index = cachedJobs.indexOfFirst { it.jobId == jobDomainModel.jobId }
        return if (index >= 0) {
            cachedJobs[index] = jobDomainModel
            true
        } else false
    }

    override suspend fun removeJob(jobDomainModel: JobDomainModel): Boolean {
        return cachedJobs.removeIf { it.jobId == jobDomainModel.jobId }
    }
}
