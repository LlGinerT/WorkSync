package com.synctech.worksync.ui.screens.inventoryPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.models.ActiveSessionContext
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.ItemsDomainModel
import com.synctech.worksync.domain.repositories.InventoryRepository
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar el estado y operaciones del inventario.
 *
 * Utiliza directamente el [InventoryRepository] para acceder a los datos
 * y el [ActiveSessionContext] para obtener el usuario activo.
 *
 * @property inventoryRepository Repositorio de inventario (mediador).
 */
class InventoryViewModel(
    private val inventoryRepository: InventoryRepository,
) : ViewModel() {

    /** Estado observable de la interfaz de usuario. */
    private val _uiState = MutableStateFlow(InventoryState())
    val uiState: StateFlow<InventoryState> = _uiState.asStateFlow()

    init {
        fetchInventory()
    }

    /**
     * Recupera el inventario completo desde el repositorio.
     * Aplica transformación de modelo de dominio a modelo de UI.
     */
    private fun fetchInventory() {
        viewModelScope.launch {
            _uiState.update { it.copy(showLoadingIndicator = true) }

            inventoryRepository.getItems()
                .onSuccess { items ->
                    _uiState.update {
                        it.copy(
                            inventory = items.map { item -> item.toUi() },
                            showLoadingIndicator = false,
                            errorMessage = null
                        )
                    }
                    Log.i("InventoryViewModel", "Inventario cargado correctamente")
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            showLoadingIndicator = false,
                            errorMessage = error.message
                                ?: "Error desconocido al cargar inventario."
                        )
                    }
                    Log.e("InventoryViewModel", "Error al cargar inventario", error)
                }
        }
    }
    //TODO: Extraer el update a un caso de uso, para poder usarlo cuando se cierre un Job actulizar el inventario
    /**
     * Actualiza un material del inventario.
     *
     * @param item El material actualizado.
     */
    fun updateItem(item: ItemsDomainModel) {
        viewModelScope.launch {
            inventoryRepository.updateItem(EmployeeDomainModel.SYSTEM_USER, item)
                .onSuccess {
                    fetchInventory()
                    Log.i("InventoryViewModel", "Material actualizado correctamente")
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            errorMessage = error.message ?: "No se pudo actualizar el material."
                        )
                    }
                    Log.e("InventoryViewModel", "Error al actualizar material", error)
                }
        }
    }
}



