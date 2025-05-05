package com.synctech.worksync.ui.screens.jobsPanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.domain.repositories.JobsRepository
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsable de gestionar la lista de trabajos disponibles.
 *
 * Utiliza el [JobsRepository] y accede al usuario activo a través del [CacheActiveSessionRepository].
 *
 * @param jobsRepository Repositorio central de trabajos (mediador).
 * @param sessionCache Repositorio de sesión en caché.
 */
class JobsViewModel(
    private val jobsRepository: JobsRepository,
    private val sessionCache: CacheActiveSessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobsState())
    val uiState: StateFlow<JobsState> = _uiState.asStateFlow()

    init {
        fetchJobs()
    }

    /**
     * Carga los trabajos desde el repositorio y actualiza el estado de la UI.
     */
    private fun fetchJobs() {
        val user = sessionCache.getUser() ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(showLoadingIndicator = true, errorMessage = null) }

            runCatching {
                jobsRepository.getJobs(user)
            }.onSuccess { jobs ->
                _uiState.update {
                    it.copy(
                        jobsList = jobs.map { job -> job.toUi() }, showLoadingIndicator = false
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message ?: "Error desconocido",
                        showLoadingIndicator = false
                    )
                }
            }
        }
    }
}
