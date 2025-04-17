package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.JobDomainModel

/**
 * Interfaz que define las operaciones para gestionar trabajos en la aplicación.
 */
interface JobsRepository {
    //He quitado todos los User que pedias, ya que de la validacion de permisos se debe encargar
    // el caso de uso o el viewModel, la base de datos solo hace CRUD. Hacemos un getAll a local y luego
    // se trabaja con ellos, tambien he quitado el assignWork, por que es mas facil hacer una polivalente de
    // updateWork, asi nos sirve tambien para meterle materiales, darla por finalizarla etc.

    /**
     * Obtiene la lista de trabajos disponibles.
     * @return Lista de trabajos disponibles para el usuario.
     */
    fun getJobs(): List<JobDomainModel>

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
