package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

/**
 * Implementación simulada del repositorio de empleados.
 * Proporciona datos de ejemplo para pruebas en local.
 */
class MockEmployeesRepository : EmployeesRepository {

    private val workerList = mutableListOf(
        EmployeeDomainModel("1", "Pepito Encargado Lopez", true),
        EmployeeDomainModel("2", "Laura Baeza Ruiz", false),
        EmployeeDomainModel("3", "Luis Giner Tendero", false)
    )

    /**
     * Devuelve un empleado por su ID.
     *
     * @param userId ID del empleado.
     * @return El empleado correspondiente, o null si no se encuentra.
     */
    override fun getEmployee(userId: String): EmployeeDomainModel? {
        return workerList.firstOrNull { it.userId == userId }
    }

    /**
     * Devuelve la lista completa de empleados.
     *
     * @return Lista de empleados.
     */
    override fun getEmployeeList(): List<EmployeeDomainModel> {
        return workerList.toList()
    }

    /**
     * Reemplaza completamente la lista de empleados.
     * TODO: Cambiara cuando firebase este implementado
     * @param list Nueva lista de empleados a guardar.
     * @return true si se reemplazó correctamente.
     */
    override fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean {
        workerList.clear()
        workerList.addAll(list)
        return true
    }
}

