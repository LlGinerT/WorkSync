package com.synctech.worksync.ui.screens.materialPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.useCases.GetMaterialUseCase
import com.synctech.worksync.ui.models.toUi
import com.synctech.worksync.ui.screens.materialPanel.MaterialState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel para gestionar el estado y la lógica de negocio de la pantalla de materiales.
 *
 * @param getMaterialUseCase Caso de uso para obtener la lista de materiales desde el dominio.
 */
class MaterialViewModel(
    private val getMaterialUseCase: GetMaterialUseCase,
) : ViewModel() {

    /** Estado de la interfaz de usuario representado mediante StateFlow. */
    private val _uiState = MutableStateFlow(MaterialState())
    val uiState: StateFlow<MaterialState> = _uiState.asStateFlow()

    init {
        Log.d("MaterialViewModel", "ViewModel inicializado.")
        fetchMaterials()
    }

    /**
     * Obtiene la lista de materiales y actualiza el estado de la interfaz de usuario.
     * Maneja el proceso en un coroutine utilizando `viewModelScope` y `Dispatchers.IO`.
     */
    private fun fetchMaterials() = viewModelScope.launch {
        _uiState.update { it.copy(showLoadingIndicator = true) }
        Log.d("WorkViewModel", "Iniciando la carga de materiales...")
        try {
            val materialDomain = withContext(Dispatchers.IO) {
                Log.d("MaterialViewModel", "Llamando a getMaterialUseCase para obtener materiales...")
                getMaterialUseCase()
            }
            _uiState.update {
                it.copy(
                    showLoadingIndicator = false,
                    materials = materialDomain.map { it.toUi() }
                )
            }
            Log.d("MaterialViewModel", "Materiales cargados exitosamente.")
        } catch (e: Exception) {
            Log.e("MaterialViewModel", "Error al cargar materiales", e)
            _uiState.update { it.copy(showLoadingIndicator = false) }
        }
    }
}
