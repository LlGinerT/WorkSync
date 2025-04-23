package com.synctech.worksync.domain.useCases

import android.util.Log
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository

/**
 * Clase que representa un caso de uso para obtener una lista de trabajos desde el repositorio.
 *
 * @param jobsRepository El repositorio de trabajos, donde se obtiene la información.
 */
class GetJobsUseCase(
    private val jobsRepository: JobsRepository
) {
    /**
     * Obtiene una lista de trabajos desde el repositorio y se filtra por 'isAdmin'.
     ** @param user [EmployeeDomainModel], que solicita los trabajos.
     ** @return Una lista de objetos [JobDomainModel], representando los trabajos obtenidos.
     */
    suspend operator fun invoke(user: EmployeeDomainModel): Result<List<JobDomainModel>> {
        return try {
            val jobList = jobsRepository.getJobs(user)
            Log.i(
                "GetJobsUseCase",
                "Trabajos encontrados para ${if (user.isAdmin) "admin" else "usuario"} ${user.userId}: ${jobList.size}"
            )
            Result.success(jobList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
