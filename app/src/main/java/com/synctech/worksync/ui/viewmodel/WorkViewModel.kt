package com.synctech.worksync.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.useCases.GetWorkUseCase
import com.synctech.worksync.ui.models.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkViewModel(
    private val getWorkUseCase: GetWorkUseCase,

) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkState())
    val uiState: StateFlow<WorkState> = _uiState

    init {
        fetchWorks()
    }

    private fun fetchWorks() = viewModelScope.launch {
        _uiState.update { it.copy(showLoadingIndicator = true) }

        try {
            val worksUIModel = getWorkUseCase().toUI()
            _uiState.update {
                it.copy(
                    works = worksUIModel.works, // Corregido para acceder correctamente a la lista
                    title = worksUIModel.title,
                    showLoadingIndicator = false
                )
            }
        } catch (e: Exception) {
            _uiState.update { it.copy(showLoadingIndicator = false) }
        }
    }
}
