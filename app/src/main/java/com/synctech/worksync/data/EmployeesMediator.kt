package com.synctech.worksync.data

import android.util.Log
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.repositories.EmployeesRepository

/**
 * Mediador entre el repositorio remoto (mock o Firebase) y el de caché.
 * Prioriza el acceso rápido y reduce llamadas innecesarias a la fuente remota.
 */
class EmployeesMediator(
    private val remote: EmployeesRepository,
    private val cache: EmployeesRepository
) : EmployeesRepository {

    /**
     * Obtiene un empleado primero desde caché, y si no está,
     * consulta la fuente remota y actualiza la caché.
     */
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
        }

        Log.i("EmployeesMediator", "getEmployee($userId): actualizada CACHE desde REMOTO")
        return cache.getEmployee(userId)
    }

    /**
     * Obtiene la lista de empleados, primero desde caché si no está vacía.
     * Si la caché está vacía, consulta remoto y la actualiza.
     */
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

    /**
     * Actualiza tanto la caché como el remoto, solo si detecta diferencias.
     *
     * TODO: En el futuro, este método se mejorará para aplicar actualizaciones parciales en Firebase
     * (por ejemplo, usando diffs o llamadas específicas por usuario).
     */
    override fun updateEmployeeList(list: List<EmployeeDomainModel>): Boolean {
        Log.i("EmployeesMediator", "actualizando CACHE")
        cache.updateEmployeeList(list)

        val remoteList = remote.getEmployeeList()
        return if (list != remoteList) {
            Log.i("EmployeesMediator", "CACHE != REMOTO → actualizando REMOTO")
            remote.updateEmployeeList(list)
        } else {
            Log.i("EmployeesMediator", "CACHE == REMOTO → no se actualiza REMOTO")
            true
        }
    }
}
