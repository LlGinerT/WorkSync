package com.synctech.worksync.data

import android.util.Log
import com.synctech.worksync.data.cache.CacheInventoryRepository
import com.synctech.worksync.data.testData.MockInventoryRepository
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel
import com.synctech.worksync.domain.repositories.InventoryRepository

/**
 * Mediador entre la caché local y el repositorio remoto simulado (mock).
 * Coordina cuándo obtener datos locales o remotos.
 *
 * Actualmente utiliza MockInventoryRepository como repositorio remoto para simular Firebase.
 */
class InventoryMediator(
    private val cache: CacheInventoryRepository,
    private val remote: MockInventoryRepository // Mock simula remoto (Firebase)
) : InventoryRepository {

    /**
     * Obtiene la lista de materiales, priorizando la caché local.
     * Si la caché está vacía, sincroniza desde el repositorio remoto mock.
     */
    override suspend fun getItems(): Result<List<ItemsDomainModel>> {
        val cachedItems = cache.getItems().getOrNull()
        return if (!cachedItems.isNullOrEmpty()) {
            Log.i("InventoryMediator", "Obteniendo datos de caché local")
            Result.success(cachedItems)
        } else {
            Log.i("InventoryMediator", "Obteniendo datos de repositorio remoto")
            remote.getItems().onSuccess { items ->
                items.forEach { cache.addItem(EmployeeDomainModel.SYSTEM_USER, it) }
            }
        }
    }

    /**
     * Añade un material tanto local como remotamente (mock).
     */
    override suspend fun addItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        return remote.addItem(employee, item).onSuccess {
            cache.addItem(employee, item)
        }
    }

    /**
     * Actualiza un material tanto local como remotamente (mock).
     */
    override suspend fun updateItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        return remote.updateItem(employee, item).onSuccess {
            cache.updateItem(employee, item)
        }
    }

    /**
     * Elimina un material tanto local como remotamente (mock).
     */
    override suspend fun removeItem(
        employee: EmployeeDomainModel,
        item: ItemsDomainModel
    ): Result<Boolean> {
        return remote.removeItem(employee, item).onSuccess {
            cache.removeItem(employee, item)
        }
    }
}
