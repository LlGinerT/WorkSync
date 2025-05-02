package com.synctech.worksync.ui.screens.inventoryPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.useCases.inventory.GetInventoryUseCase
import com.synctech.worksync.ui.models.toUi
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
 * @param getInventoryUseCase Caso de uso para obtener la lista de materiales desde el dominio.
 */
class InventoryViewModel(
    private val getInventoryUseCase: GetInventoryUseCase,
) : ViewModel() {

    /** Estado de la interfaz de usuario representado mediante StateFlow. */
    private val _uiState = MutableStateFlow(InventoryState())
    val uiState: StateFlow<InventoryState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchMaterials()
            Log.i("InventoryViewModel", "ViewModel inicializado.")
        }
    }

    /**
     * Obtiene la lista de materiales y actualiza el estado de la interfaz de usuario.
     * Maneja el proceso en un coroutine utilizando `viewModelScope` y `Dispatchers.IO`.
     */
    private suspend fun fetchMaterials() {
        _uiState.update { it.copy(showLoadingIndicator = true) }

        val result = withContext(Dispatchers.IO) {
            getInventoryUseCase()
        }

        result.onSuccess { items ->
            _uiState.update {
                it.copy(
                    inventory = items.map { item -> item.toUi() },
                    showLoadingIndicator = false,
                    errorMessage = null
                )
            }
            Log.i("InventoryViewModel", "Inventario cargado con exito: ${items.size}")
        }.onFailure { error ->
            _uiState.update {
                it.copy(
                    showLoadingIndicator = false,
                    errorMessage = error.message ?: "Error desconocido"
                )
            }
            Log.e("InventoryViewModel", "Error obteniendo inventario: ${error.message}", error)
        }

    }
}

