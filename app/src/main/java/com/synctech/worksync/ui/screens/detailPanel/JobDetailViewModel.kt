package com.synctech.worksync.ui.screens.detailPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.synctech.worksync.domain.exceptions.JobsErrors
import com.synctech.worksync.domain.useCases.GetJobByIdUseCase
import com.synctech.worksync.ui.models.toUi
import com.synctech.worksync.ui.session.SessionViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel para la gestión de detalles de un trabajo.
 *
 * @param getJobByIdUseCase [GetJobByIdUseCase]Caso de uso para obtener un trabajo por su ID.
 * @param sessionViewModel [SessionViewModel] compartido que gestiona la sesion iniciada
 * */
class JobDetailViewModel(
    private val getJobByIdUseCase: GetJobByIdUseCase,
    private val sessionViewModel: SessionViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobDetailState())
    val uiState: StateFlow<JobDetailState> = _uiState

    /**
     * Metodo para cargar los detalles de un trabajo por id.
     *
     * @param jobId [String] ID del trabajo a cargar y añadirlo al state.
     * */
    suspend fun loadWorkDetail(jobId: String) {
        _uiState.update { it.copy(showLoadingIndicator = true) }

        val currentUser = sessionViewModel.currentUser

        currentUser?.let { it ->
            val result = getJobByIdUseCase(jobId = jobId, it)
            result
                .onSuccess { job ->
                    _uiState.update { state ->
                        state.copy(job = job.toUi(), showLoadingIndicator = false)
                    }
                    Log.i("JobDetailViewModel", "Trabajo cargado: ${job.jobId}")
                }
                .onFailure { error ->
                    val message = when (error) {
                        is JobsErrors.NotFound -> {
                            Log.w("JobDetailViewModel", "Warning: ${error.message}")
                            "El trabajo no existe."
                        }

                        is JobsErrors.Unauthorized -> {
                            Log.w("JobDetailViewModel", "Warning: ${error.message}")
                            "No tienes permiso para ver este trabajo."
                        }

                        else -> {
                            Log.e("JobDetailViewModel", "Error inesperado", error)
                            "Error inesperado"
                        }
                    }
                    _uiState.update {
                        it.copy(
                            errorMessage = message,
                            showLoadingIndicator = false
                        )
                    }
                }
        } ?: run {
            _uiState.update { state ->
                state.copy(
                    errorMessage = "Sesión no iniciada",
                    showLoadingIndicator = false
                )
            }
            Log.w("JobDetailViewModel", "Error: ${_uiState.value.errorMessage}")
        }
    }
}
