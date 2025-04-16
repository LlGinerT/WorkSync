package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.models.Work

/**
 * Interfaz que define las operaciones para gestionar trabajos en la aplicación.
 */
interface WorkRepository {

    /**
     * Obtiene la lista de trabajos disponibles.
     * - Si el usuario es administrador, obtiene todos los trabajos.
     * - Si el usuario es normal, obtiene solo los trabajos asignados a él.
     *
     * @param user Usuario que solicita los trabajos.
     * @return Lista de trabajos disponibles para el usuario.
     */
    fun getWork(user: User): List<Work>

    /**
     * Agrega un nuevo trabajo a la lista de trabajos. Solo un administrador puede hacerlo.
     *
     * @param user Usuario que intenta agregar un trabajo.
     * @param work Trabajo a agregar.
     * @return `true` si el trabajo fue agregado, `false` si no se tenía permiso.
     */
    fun addWork(user: User, work: Work): Boolean

    /**
     * Asigna un trabajo a un usuario específico. Solo un administrador puede asignar trabajos.
     *
     * @param user Usuario administrador que realiza la asignación.
     * @param workId ID del trabajo a asignar.
     * @param assignedUser Usuario al que se asignará el trabajo.
     * @return `true` si la asignación fue exitosa, `false` en caso contrario.
     */
    fun assignWork(user: User, workId: Int, assignedUser: User): Boolean

    /**
     * Elimina un trabajo de la lista. Solo un administrador puede hacerlo.
     *
     * @param user Usuario que intenta eliminar un trabajo.
     * @param work Trabajo a eliminar.
     * @return `true` si el trabajo fue eliminado, `false` si no se tenía permiso.
     */
    fun removeWork(user: User, work: Work): Boolean

}
