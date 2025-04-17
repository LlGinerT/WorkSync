package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.exceptions.JobNotFoundException
import com.synctech.worksync.domain.exceptions.UnauthorizedAccessException
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository


class GetJobByIdUseCase(
    private val repository: JobsRepository
) {
    /**
     * Devuelve el trabajo por ID si el usuario tiene permiso para verlo.
     * Admins pueden ver cualquier trabajo. Los usuarios normales solo los asignados a ellos.
     */
    operator fun invoke(
        jobId: String,
        user: EmployeeDomainModel
    ): Result<JobDomainModel> {
        val job = repository.getJobById(jobId)
            ?: return Result.failure(JobNotFoundException(jobId))

        if (!user.isAdmin && job.assignedTo != user.userId) {
            return Result.failure(UnauthorizedAccessException())
        }

        return Result.success(job)
    }
}
