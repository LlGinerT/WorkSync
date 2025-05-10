package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository

class MockJobsDataRepository : JobsRepository {

    // Lista simulada de trabajos
    private val mockJobDomainModelData = mutableListOf(
        JobDomainModel(
            jobId = "1",
            jobName = "Instalación Cable Fibra Optica",
            clientName = "Andrés Sanz",
            description = "",
            address = "La Latina",
            assignedTo = "3"
        ),
        JobDomainModel(
            jobId = "2",
            jobName = "Instalación Router",
            clientName = "Juan Pérez",
            description = "Instalación de Router",
            address = "Gran Vía",
            assignedTo = "3"
        ),
        JobDomainModel(
            jobId = "3",
            jobName = "Configuración de Red Wifi",
            clientName = "Elisa Main",
            description = "",
            address = "Sol",
            assignedTo = "2"
        ),
        JobDomainModel(
            jobId = "4",
            jobName = "Optimización de la Señal Red Wifi",
            clientName = "Daniel Rodriquez",
            description = "Mejorar calidad WIFI",
            address = "Chamberí",
            assignedTo = "3"
        ),
        JobDomainModel(
            jobId = "5",
            jobName = "Configuración de Red Local",
            clientName = "Sara Ban",
            description = "Configuración red local.",
            address = "Malasaña",
            assignedTo = null
        )
    )

    override suspend fun getJobs(user: EmployeeDomainModel): List<JobDomainModel> {
        val jobs = mockJobDomainModelData
        return if (user.isAdmin) {
            jobs
        } else {
            jobs.filter { it.assignedTo == user.userId }
        }
    }

    override suspend fun getJobById(jobId: String): JobDomainModel? {
        return mockJobDomainModelData.firstOrNull { it.jobId == jobId }
    }

    override suspend fun addJob(jobDomainModel: JobDomainModel): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateJob(jobDomainModel: JobDomainModel): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun removeJob(jobDomainModel: JobDomainModel): Boolean {
        TODO("Not yet implemented")
    }
}
