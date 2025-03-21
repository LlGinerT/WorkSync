package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.domainModels.WorkerModel

/**
 * Interfaz para acceder a los datos de los trabajadores.
 */
interface WorkersRepository {
    /**
     * Obtiene el trabajador por su ID.
     *
     * @param userId ID del trabajador.
     * @return El modelo del trabajador correspondiente.
     */
    fun getWorker(userId: String): WorkerModel?

    /**
     * Devuelve la lista de todos los trabajadores.
     *
     * @return Lista de trabajadores.
     */
    fun getWorkersList(): List<WorkerModel> // Para que el admin pueda asignar los trabajos.
}