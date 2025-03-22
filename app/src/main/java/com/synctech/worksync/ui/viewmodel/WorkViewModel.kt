package com.synctech.worksync.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.useCases.GetWorkUseCase
import com.synctech.worksync.ui.models.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkViewModel(
    private val getWorkUseCase: GetWorkUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkState())
    val uiState: StateFlow<WorkState> = _uiState

    init {
        fetchWorks()
    }

    private fun fetchWorks() = viewModelScope.launch {
        // Mostrar el indicador de carga mientras se obtiene la información
        _uiState.update { it.copy(showLoadingIndicator = true) }

        try {
            // Realizar la operación de obtención de datos en un hilo en segundo plano
            val workDomain = withContext(Dispatchers.IO) {
                getWorkUseCase()
            }

            // Convertir los datos del dominio al modelo de UI
            val workUIModel = workDomain.map { it.toUI() }

            // Actualizar el estado con los datos obtenidos
            _uiState.update {
                it.copy(
                    works = workUIModel,
                    showLoadingIndicator = false
                )
            }
        } catch (e: Exception) {
            _uiState.update { it.copy(showLoadingIndicator = false) }
        }
    }
}
