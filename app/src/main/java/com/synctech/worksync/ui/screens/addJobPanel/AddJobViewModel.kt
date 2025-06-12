package com.synctech.worksync.ui.screens.addJobPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.models.JobDomainModel
import com.synctech.worksync.domain.repositories.JobsRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class AddJobViewModel(
    private val jobsRepository: JobsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddJobState())
    val uiState: StateFlow<AddJobState> = _uiState

    private val _eventFlow = MutableSharedFlow<AddJobUiEvent>()
    val eventFlow: SharedFlow<AddJobUiEvent> = _eventFlow.asSharedFlow()

    fun onJobNameChanged(jobName: String) {
        _uiState.update { it.copy(jobName = jobName) }
    }

    fun onClientNameChanged(clientName: String) {
        _uiState.update { it.copy(clientName = clientName) }
    }

    fun onAddressChanged(address: String) {
        _uiState.update { it.copy(address = address) }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun onAssignedToChanged(assignedTo: String) {
        _uiState.update { it.copy(assignedTo = assignedTo) }
    }

    fun save() {
        val job = JobDomainModel(
            jobId = UUID.randomUUID().toString(),
            jobName = _uiState.value.jobName,
            clientName = _uiState.value.clientName,
            address = _uiState.value.address,
            description = _uiState.value.description,
            assignedTo = _uiState.value.assignedTo
        )
        viewModelScope.launch {
            val result = jobsRepository.createJob(job)
            if (result) {
                _eventFlow.emit(AddJobUiEvent.JobSaved)
            } else {
                Log.d("AddJobViewModel", "Error al guardar el trabajo")
            }
        }
    }

    fun cancel() {
        viewModelScope.launch {
            _uiState.value = AddJobState()
            _eventFlow.emit(AddJobUiEvent.JobCancelled)
        }
    }
}