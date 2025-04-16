package com.synctech.worksync.ui.screens.workPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.useCases.GetWorkUseCase
import com.synctech.worksync.ui.models.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel para la gestión de trabajos.
 *
 * @param getWorkUseCase Caso de uso para obtener los trabajos.
 * @param currentUser Usuario actual con su rol.
 */
class WorkViewModel(
    private val getWorkUseCase: GetWorkUseCase,
    private val currentUser: User // Usuario autenticado
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkState(user = currentUser))
    val uiState: StateFlow<WorkState> = _uiState

    init {
        Log.d("WorkViewModel", "ViewModel inicializado.")
        fetchWorks()
    }

    /**
     * Obtiene los trabajos y actualiza el estado de la UI.
     */
    private fun fetchWorks() = viewModelScope.launch {

        _uiState.update { it.copy(showLoadingIndicator = true) }
        Log.d("WorkViewModel", "Iniciando la carga de trabajos...")
        try {
            val workDomain = withContext(Dispatchers.IO) {
                Log.d("WorkViewModel", "Llamando a getWorkUseCase para obtener trabajos...")
                getWorkUseCase(currentUser)

            }

            val workUIModel = workDomain.map { it.toUI() }

            // Filtrar los trabajos asignados al usuario si no es admin
            val filteredWorks = if (currentUser.isAdmin) {
                workUIModel // Admin ve todos los trabajos
            } else {
                workUIModel.filter { it.assignedTo == currentUser.userId } // Usuario normal ve solo los asignados
            }

            // Log para verificar los trabajos obtenidos
            Log.d("WorkViewModel", "Trabajos obtenidos: ${workUIModel.size}")
            workUIModel.take(10).forEachIndexed { index, work ->
                Log.d("WorkViewModel", "Trabajo #${index + 1}: $work")
            }

            // Actualizar el estado con los trabajos obtenidos y filtrados
            _uiState.update {
                it.copy(
                    works = workUIModel,
                    filteredWorks = filteredWorks, // Agregamos la lista filtrada
                    showLoadingIndicator = false
                )
            }
        } catch (e: Exception) {
            Log.e("WorkViewModel", "Error obteniendo trabajos", e)
            _uiState.update { it.copy(showLoadingIndicator = false) }
        }
    }
}
