package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel

/**
 * Repositorio que define las operaciones relacionadas con los materiales.
 */
interface InventoryRepository {

    /**
     * Obtiene la lista de materiales disponibles.
     *
     * @return Lista de materiales.
     */
    fun getMaterials(): List<ItemsDomainModel>

    /**
     * Agrega un itemsDomainModel al sistema.
     *
     * @param employee Usuario que realiza la acción.
     * @param itemsDomainModel ItemsDomainModel a agregar.
     * @return `true` si el itemsDomainModel se agregó correctamente, `false` en caso contrario.
     */
    fun addMaterials(employee: EmployeeDomainModel, itemsDomainModel: ItemsDomainModel): Boolean

    /**
     * Elimina un itemsDomainModel del sistema.
     *
     * @param employee Usuario que realiza la acción.
     * @param itemsDomainModel ItemsDomainModel a eliminar.
     * @return `true` si el itemsDomainModel se eliminó correctamente, `false` en caso contrario.
     */
    fun removeMaterials(employee: EmployeeDomainModel, itemsDomainModel: ItemsDomainModel): Boolean
}
