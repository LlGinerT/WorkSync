package com.synctech.worksync.domain.useCases.inventory

import android.util.Log
import com.synctech.worksync.data.testData.MockInventoryDataRepository
import com.synctech.worksync.domain.models.ItemsDomainModel

/**
 * Caso de uso para obtener la lista de materiales desde el repositorio.
 *
 * @param inventoryRepository Repositorio de materiales utilizado para obtener los datos.
 */
class GetInventoryUseCase(
    private val inventoryRepository: MockInventoryDataRepository
) {

    /**
     * Ejecuta el caso de uso para obtener los materiales.
     *
     * @return Lista de materiales disponibles.
     */
    suspend operator fun invoke(): Result<List<ItemsDomainModel>> {
        return try {
            val itemList = inventoryRepository.getItems()
            Log.i("GetInventoryUseCase", "Inventario encontrado: ${itemList.size}")
            Result.success(itemList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

