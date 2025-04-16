package com.synctech.worksync.ui.screens.detailPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.useCases.GetWorkByIdUseCase
import com.synctech.worksync.ui.models.DetailUIModel
import com.synctech.worksync.ui.models.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val getWorkByIdUseCase: GetWorkByIdUseCase,
    private val currentUser: User
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailState())
    val uiState: StateFlow<DetailState> = _uiState

    fun loadWorkDetail(workId: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        try {
            val work = withContext(Dispatchers.IO) {
                getWorkByIdUseCase(workId)
            }

            if (work == null) throw Exception("Trabajo no encontrado.")

            // Verificamos permisos
            if (!currentUser.isAdmin && work.assignedTo != currentUser.userId) {
                throw SecurityException("No tienes acceso a este trabajo.")
            }

            val uiModel = work.toUI()

            _uiState.update {
                it.copy(
                    work = uiModel,
                    isLoading = false,
                    errorMessage = null
                )
            }
        } catch (e: Exception) {
            Log.e("DetailViewModel", "Error cargando trabajo", e)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    errorMessage = "Error al cargar detalles del trabajo."
                )
            }
        }
    }
}
