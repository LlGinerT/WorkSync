package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.JobDomainModel

/**
 * Interfaz que define las operaciones para gestionar trabajos en la aplicación.
 */
interface JobsRepository {
    // me equivoque perdon, quite los user y le pase la logica de validacion a el UseCase, pero me
    // di cuenta mas tarde que era un fallo de seguirad, lo ire recomponiendo mientras avanzamos.
    // mil perdones
    /**
     * Obtiene la lista de trabajos disponibles.
     *
     * @param user [EmployeeDomainModel] que hace la petición.
     * @return Lista de [JobDomainModel] disponibles para el usuario.
     */
    fun getJobs(user: EmployeeDomainModel): List<JobDomainModel>

    /**
     * Recupera un Job por su ID,
     * preferiblemente del Cache(En el futuro cuando conectemos a Firebase)
     *
     * @param jobId [String] id del trabajo a recuperar
     * @return [JobDomainModel]
     * */
    fun getJobById(jobId: String): JobDomainModel?

    /**
     * Agrega un nuevo trabajo a la lista de trabajos. Solo un administrador puede hacerlo.
     *
     * @param jobDomainModel Trabajo a agregar.
     * @return `true` si el trabajo fue agregado, `false` si no se tenía permiso.
     */
    fun addJob(jobDomainModel: JobDomainModel): Boolean

    fun updateJob(jobDomainModel: JobDomainModel): Boolean

    /**
     * Elimina un trabajo de la lista. Solo un administrador puede hacerlo.
     *
     * @param jobDomainModel Trabajo a eliminar.
     * @return `true` si el trabajo fue eliminado, `false` si no se tenía permiso.
     */
    fun removeJob(jobDomainModel: JobDomainModel): Boolean

}
