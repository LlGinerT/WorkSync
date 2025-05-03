package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel
import com.synctech.worksync.domain.repositories.InventoryRepository

/**
 * Implementación mock del repositorio para pruebas unitarias o desarrollo inicial.
 * Proporciona datos ficticios.
 */
class MockInventoryRepository : InventoryRepository {

    private val mockInventoryData = mutableListOf(
        ItemsDomainModel(materialId = 1, name = "Cable de Red", precio = 100.50, cantidad = 35),
        ItemsDomainModel(materialId = 2, name = "Adaptador RJ45", precio = 170.50, cantidad = 60),
        ItemsDomainModel(materialId = 3, name = "Módem", precio = 65.50, cantidad = 120),
        ItemsDomainModel(materialId = 4, name = "Switch de Red", precio = 80.50, cantidad = 46)
    )

    override suspend fun getItems(): Result<List<ItemsDomainModel>> =
        Result.success(mockInventoryData)

    override suspend fun addItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        mockInventoryData.add(item)
        return Result.success(true)
    }

    override suspend fun updateItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        val index = mockInventoryData.indexOfFirst { it.materialId == item.materialId }
        return if (index >= 0) {
            mockInventoryData[index] = item
            Result.success(true)
        } else {
            Result.failure(Exception("Material no encontrado en datos mock."))
        }
    }

    override suspend fun removeItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        val removed = mockInventoryData.removeIf { it.materialId == item.materialId }
        return if (removed) Result.success(true)
        else Result.failure(Exception("Material no encontrado en datos mock."))
    }
}
