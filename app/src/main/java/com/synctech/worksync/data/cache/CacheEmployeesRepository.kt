package com.synctech.worksync.data.cache

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

/**
 * Repositorio de caché en memoria de empleados.
 * Utilizado para acceder rápidamente a datos ya consultados.
 */
class CacheEmployeesRepository : EmployeesRepository {

    private val cache: MutableList<EmployeeDomainModel> = mutableListOf()

    /**
     * Devuelve un empleado desde la caché.
     *
     * @param userId ID del empleado.
     * @return Empleado si está en caché, o null.
     */
    override fun getEmployee(userId: String): EmployeeDomainModel? {
        return cache.firstOrNull { it.userId == userId }
    }

    /**
     * Devuelve la lista de empleados almacenada en caché.
     *
     * @return Lista de empleados en caché.
     */
    override fun getEmployeeList(): List<EmployeeDomainModel> {
        return cache.toList()
    }

    /**
     * Reemplaza la lista en caché con la nueva.
     * TODO: Cambiara cuando firebase este implementado
     * @param list Nueva lista de empleados.
     * @return true si se actualizó correctamente.
     */
    override fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean {
        cache.clear()
        cache.addAll(list)
        return true
    }
}

