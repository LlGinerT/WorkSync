package com.synctech.worksync.ui.session

import androidx.lifecycle.ViewModel
import com.synctech.worksync.domain.domainModels.WorkerDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel compartido que almacena la sesión del usuario logueado.
 */
class SessionViewModel : ViewModel() {

    private val _worker = MutableStateFlow<WorkerDomainModel?>(null)
    val worker: StateFlow<WorkerDomainModel?> = _worker.asStateFlow()

    /**
     * Establece el trabajador autenticado en sesión.
     *
     * @param workerDomainModel Modelo del trabajador.
     */
    fun setWorker(workerDomainModel: WorkerDomainModel) {
        _worker.value = workerDomainModel
    }

    /**
     * Cierra la sesión del usuario.
     */
    fun logout() {
        _worker.value = null
    }
}