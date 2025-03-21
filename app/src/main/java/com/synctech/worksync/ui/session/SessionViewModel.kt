package com.synctech.worksync.ui.session

import androidx.lifecycle.ViewModel
import com.synctech.worksync.domain.domainModels.WorkerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel compartido que almacena la sesión del usuario logueado.
 */
class SessionViewModel : ViewModel() {

    private val _worker = MutableStateFlow<WorkerModel?>(null)
    val worker: StateFlow<WorkerModel?> = _worker.asStateFlow()

    /**
     * Establece el trabajador autenticado en sesión.
     *
     * @param workerModel Modelo del trabajador.
     */
    fun setWorker(workerModel: WorkerModel) {
        _worker.value = workerModel
    }

    /**
     * Cierra la sesión del usuario.
     */
    fun logout() {
        _worker.value = null
    }
}