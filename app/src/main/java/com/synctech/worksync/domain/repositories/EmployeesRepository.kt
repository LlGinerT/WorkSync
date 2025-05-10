package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.EmployeeDomainModel

/**
 * Interfaz para acceder a los datos de los trabajadores.
 */
interface EmployeesRepository {
    /**
     * Obtiene el trabajador por su ID.
     *
     * @param userId ID del trabajador.
     * @return El modelo del trabajador correspondiente.
     */
    fun getEmployee(userId: String): EmployeeDomainModel?

    /**
     * Devuelve la lista de todos los trabajadores.
     *
     * @return Lista de trabajadores.
     */
    fun getEmployeeList(): List<EmployeeDomainModel> // Para que el admin pueda asignar los trabajos.

    fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean
}