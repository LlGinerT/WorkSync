package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel
import com.synctech.worksync.domain.repositories.InventoryRepository

/**
 * Implementación de prueba del repositorio de materiales.
 * Proporciona datos ficticios para simular la interacción con un repositorio real.
 */
class MockInventoryDataRepository : InventoryRepository {
    private val itemsDomainModels = mutableListOf(
        ItemsDomainModel(
            materialId = 1,
            name = "Cable De Red",
            precio = 100.50,
            cantidad = 35
        ),
        ItemsDomainModel(
            materialId = 2,
            name = "Adaptador RJ45",
            precio = 170.50,
            cantidad = 60
        ),
        ItemsDomainModel(
            materialId = 3,
            name = "Módem",
            precio = 65.50,
            cantidad = 120
        ),
        ItemsDomainModel(
            materialId = 4,
            name = "Switch de Red",
            precio = 80.50,
            cantidad = 46
        ),
    )

    /**
     * Obtiene la lista de materiales disponibles en el repositorio simulado.
     *
     * @return Lista de materiales.
     */
    override fun getMaterials(): List<ItemsDomainModel> {
        return itemsDomainModels
    }

    /**
     * Agrega un itemsDomainModel al repositorio simulado.
     *
     * @param employee Usuario que realiza la acción de agregar el itemsDomainModel.
     * @param itemsDomainModel El itemsDomainModel a agregar.
     * @return `true` si el itemsDomainModel se agregó correctamente, siempre retorna `true` en esta implementación.
     */
    override fun addMaterials(
        employee: EmployeeDomainModel,
        itemsDomainModel: ItemsDomainModel
    ): Boolean {
        return true
    }

    /**
     * Elimina un itemsDomainModel del repositorio simulado.
     *
     * @param employee Usuario que realiza la acción de eliminar el itemsDomainModel.
     * @param itemsDomainModel El itemsDomainModel a eliminar.
     * @return `true` si el itemsDomainModel se eliminó correctamente, siempre retorna `true` en esta implementación.
     */
    override fun removeMaterials(
        employee: EmployeeDomainModel,
        itemsDomainModel: ItemsDomainModel
    ): Boolean {
        return true
    }
}
