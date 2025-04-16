package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.Material
import com.synctech.worksync.domain.models.User

/**
 * Repositorio que define las operaciones relacionadas con los materiales.
 */
interface MaterialRepository {

    /**
     * Obtiene la lista de materiales disponibles.
     *
     * @return Lista de materiales.
     */
    fun getMaterials(): List<Material>

    /**
     * Agrega un material al sistema.
     *
     * @param user Usuario que realiza la acción.
     * @param material Material a agregar.
     * @return `true` si el material se agregó correctamente, `false` en caso contrario.
     */
    fun addMaterials(user: User, material: Material): Boolean

    /**
     * Elimina un material del sistema.
     *
     * @param user Usuario que realiza la acción.
     * @param material Material a eliminar.
     * @return `true` si el material se eliminó correctamente, `false` en caso contrario.
     */
    fun removeMaterials(user: User, material: Material): Boolean
}
