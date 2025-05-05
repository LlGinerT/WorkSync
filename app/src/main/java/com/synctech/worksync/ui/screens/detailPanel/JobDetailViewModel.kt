package com.synctech.worksync.ui.screens.detailPanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.domain.exceptions.JobsError
import com.synctech.worksync.domain.useCases.jobs.GetJobByIdUseCase
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsable de gestionar el estado de detalle de un trabajo.
 *
 * Utiliza [GetJobByIdUseCase] y accede al usuario activo a través del [CacheActiveSessionRepository].
 *
 * @param getJobByIdUseCase Caso de uso para recuperar trabajos por ID.
 * @param sessionCache Repositorio de sesión en caché.
 */
class JobDetailViewModel(
    private val getJobByIdUseCase: GetJobByIdUseCase,
    private val sessionCache: CacheActiveSessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobDetailState())
    val uiState: StateFlow<JobDetailState> = _uiState

    /**
     * Carga los detalles del trabajo especificado por [jobId] y valida acceso del usuario.
     *
     * @param jobId ID del trabajo a cargar.
     */
    fun loadWorkDetail(jobId: String) {
        val user = sessionCache.getUser() ?: run {
            _uiState.update { it.copy(errorMessage = "Sesión no iniciada") }
            return
        }

        _uiState.update { it.copy(showLoadingIndicator = true) }

        viewModelScope.launch {
            getJobByIdUseCase(jobId, user).onSuccess { job ->
                _uiState.update {
                    it.copy(job = job.toUi(), showLoadingIndicator = false)
                }
            }.onFailure { error ->
                val message = when (error) {
                    is JobsError.NotFound -> "El trabajo no existe."
                    is JobsError.Unauthorized -> "No tienes permiso para ver este trabajo."
                    else -> "Error inesperado"
                }

                _uiState.update {
                    it.copy(errorMessage = message, showLoadingIndicator = false)
                }
            }
        }
    }
}
