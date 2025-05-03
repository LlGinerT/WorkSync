package com.synctech.worksync.data.cache

import android.util.Log
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel
import com.synctech.worksync.domain.repositories.InventoryRepository

/**
 * Implementación del repositorio de inventario basado en caché local.
 * Almacena temporalmente los datos para mejorar rendimiento y minimizar peticiones remotas.
 */
class CacheInventoryRepository : InventoryRepository {

    private val inventoryCache = mutableListOf<ItemsDomainModel>()

    override suspend fun getItems(): Result<List<ItemsDomainModel>> {
        Log.i("CacheInventoryRepository", "Obteniendo datos de caché local")
        return Result.success(inventoryCache)
    }

    override suspend fun addItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        inventoryCache.add(item)
        Log.i("CacheInventoryRepository", "Agregando material a caché local")
        return Result.success(true)
    }

    override suspend fun updateItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        val index = inventoryCache.indexOfFirst { it.materialId == item.materialId }
        return if (index >= 0) {
            inventoryCache[index] = item
            Log.i("CacheInventoryRepository", "Actualizando material en caché local")
            Result.success(true)
        } else {
            Log.e("CacheInventoryRepository", "Material no encontrado en la caché.")
            Result.failure(Exception("Material no encontrado en la caché."))
        }
    }

    override suspend fun removeItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        val removed = inventoryCache.removeIf { it.materialId == item.materialId }
        return if (removed) Result.success(true)
        else Result.failure(Exception("Material no encontrado en la caché."))
    }
}
