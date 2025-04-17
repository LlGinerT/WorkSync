package com.synctech.worksync.ui.screens.jobsPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.useCases.GetJobsUseCase
import com.synctech.worksync.ui.models.toUI
import com.synctech.worksync.ui.session.SessionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel para la gestión de trabajos.
 *
 * @param getJobsUseCase Caso de uso para obtener los trabajos.
 * @param sessionViewModel [SessionViewModel] compartido que gestiona la sesion iniciada
 */
class JobsViewModel(
    private val getJobsUseCase: GetJobsUseCase, sessionViewModel: SessionViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobsState())
    val uiState: StateFlow<JobsState> = _uiState

    init {
        Log.d("JobsViewModel", "ViewModel inicializado.")
        sessionViewModel.currentUser?.let { user ->
            viewModelScope.launch {
                fetchWorks(user)
            }
        }
    }


    /**
     * Obtiene los trabajos y actualiza el estado de la UI.
     */
    private fun fetchWorks(user: EmployeeDomainModel) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    showLoadingIndicator = true, errorMessage = null
                )
            }

            try {
                val jobs = withContext(Dispatchers.IO) {
                    getJobsUseCase(user)
                }.map { it.toUI() }

                _uiState.update {
                    it.copy(
                        jobsList = jobs, showLoadingIndicator = false, errorMessage = null
                    )
                }

            } catch (e: Exception) {
                Log.e("JobsViewModel", "Error obteniendo trabajos", e)
                _uiState.update {
                    it.copy(
                        showLoadingIndicator = false,
                        errorMessage = "Ha ocurrido un error al cargar los trabajos."
                    )
                }
            }
        }
    }
}