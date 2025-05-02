package com.synctech.worksync.data

import android.util.Log
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

class EmployeesMediator(
    private val remote: EmployeesRepository, private val cache: EmployeesRepository
) : EmployeesRepository {

    override fun getEmployee(userId: String): EmployeeDomainModel? {
        val cached = cache.getEmployee(userId)
        if (cached != null) {
            Log.i("EmployeesMediator", "getEmployee($userId): devuelto desde CACHE")
            return cached
        }
        val remoteList = remote.getEmployeeList()
        val cacheSuccess = cache.updateEmployeeList(remoteList)

        if (!cacheSuccess) {
            Log.w("EmployeesMediator", "getEmployee($userId): ERROR al actualizar CACHE")
            return remote.getEmployee(userId)
        } else {
            Log.i("EmployeesMediator", "getEmployee($userId): actualizada CACHE desde REMOTO")
            return cache.getEmployee(userId)
        }
    }

    override fun getEmployeeList(): List<EmployeeDomainModel> {
        val cachedList = cache.getEmployeeList()
        if (cachedList.isNotEmpty()) {
            Log.i("EmployeesMediator", "getEmployeeList(): devuelto desde CACHE")
            return cachedList
        }

        val remoteList = remote.getEmployeeList()
        val cacheSuccess = cache.updateEmployeeList(remoteList)

        if (!cacheSuccess) {
            Log.w("EmployeesMediator", "getEmployeeList(): ERROR al actualizar CACHE")
        } else {
            Log.i("EmployeesMediator", "getEmployeeList(): actualizada CACHE desde REMOTO")
        }

        return remoteList
    }

    //TODO: mejorar la logica para no tener riesgo de sobrescribir por error remote
    // -Mejorar Validaciones en domain, etc.
    override fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean {
        Log.i("EmployeesMediator", "actualizando CACHE")
        cache.updateEmployeeList(list)

        val remoteList = remote.getEmployeeList()
        return if (list != remoteList) {
            Log.i(
                "EmployeesMediator",
                "CACHE != REMOTO → actualizando REMOTO"
            )
            remote.updateEmployeeList(list)
        } else {
            Log.i(
                "EmployeesMediator",
                "CACHE == REMOTO → no se actualiza REMOTO"
            )
            true
        }
    }
}
