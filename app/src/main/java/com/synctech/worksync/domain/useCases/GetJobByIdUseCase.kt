package com.synctech.worksync.domain.useCases

import android.util.Log
import com.synctech.worksync.domain.exceptions.JobsErrors
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository

/**
 * Devuelve el trabajo por ID si el usuario tiene permiso para verlo.
 * Admins pueden ver cualquier trabajo. Los usuarios normales solo los asignados a ellos.
 *
 * @param repository [JobsRepository] Repositorio de trabajos.
 */
class GetJobByIdUseCase(
    private val repository: JobsRepository
) {
    /**
     * Metodo invoke para poder llamar al caso de uso como si fuera una funcion.
     *
     * @param jobId [String] ID del trabajo a obtener.
     * @param user [EmployeeDomainModel] Usuario autenticado.
     * */
    suspend operator fun invoke(
        jobId: String,
        user: EmployeeDomainModel
    ): Result<JobDomainModel> {
        return try {
            val job = repository.getJobById(jobId)
                ?: return Result.failure(JobsErrors.NotFound(jobId))

            if (!user.isAdmin && job.assignedTo != user.userId) {
                return Result.failure(JobsErrors.Unauthorized)
            }
            Log.i("GetJobByIdUseCase", "Trabajo encontrado: ${job.jobId}")
            return Result.success(job)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
