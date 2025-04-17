package com.synctech.worksync.ui.screens.detailPanel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.exceptions.JobNotFoundException
import com.synctech.worksync.domain.exceptions.UnauthorizedAccessException
import com.synctech.worksync.domain.useCases.GetJobByIdUseCase
import com.synctech.worksync.ui.models.toUi
import com.synctech.worksync.ui.session.SessionViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobDetailViewModel(
    private val getJobByIdUseCase: GetJobByIdUseCase,
    private val sessionViewModel: SessionViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobDetailState())
    val uiState: StateFlow<JobDetailState> = _uiState


    fun loadWorkDetail(jobId: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        val currentUser = sessionViewModel.currentUser

        currentUser?.let {
            val result = getJobByIdUseCase(jobId = jobId, it)
            result
                .onSuccess { job ->
                    _uiState.update { state ->
                        state.copy(job = job.toUi())
                    }
                }
                .onFailure { error ->
                    when (error) {
                        is JobNotFoundException -> {
                            _uiState.update { state -> state.copy(errorMessage = error.message) }
                        }

                        is UnauthorizedAccessException -> {
                            _uiState.update { state -> state.copy(errorMessage = error.message) }
                        }

                        else -> {
                            _uiState.update { state -> state.copy(errorMessage = "Error inesperdo") }
                        }
                    }
                }
        } ?: _uiState.update { it.copy(errorMessage = "Sessión no iniciada") }
    }
}
