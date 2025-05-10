package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel

/**
 * Define las operaciones relacionadas con la gestión del inventario de materiales.
 */
interface InventoryRepository {

    /**
     * Obtiene todos los materiales disponibles.
     *
     * @return [Result] con la lista de materiales o un error en caso de fallo.
     */
    suspend fun getItems(): Result<List<ItemsDomainModel>>

    /**
     * Añade un nuevo material al inventario.
     *
     * @param employee Usuario que realiza la acción.
     * @param item Material a añadir.
     * @return [Result] que indica éxito o error al añadir el material.
     */
    suspend fun addItem(employee: EmployeeDomainModel, item: ItemsDomainModel): Result<Boolean>

    /**
     * Actualiza un material existente en el inventario.
     *
     * @param employee Usuario que realiza la acción.
     * @param item Material actualizado.
     * @return [Result] que indica éxito o error al actualizar.
     */
    suspend fun updateItem(employee: EmployeeDomainModel, item: ItemsDomainModel): Result<Boolean>

    /**
     * Elimina un material del inventario.
     *
     * @param employee Usuario que realiza la acción.
     * @param item Material a eliminar.
     * @return [Result] que indica éxito o error al eliminar.
     */
    suspend fun removeItem(employee: EmployeeDomainModel, item: ItemsDomainModel): Result<Boolean>
}

