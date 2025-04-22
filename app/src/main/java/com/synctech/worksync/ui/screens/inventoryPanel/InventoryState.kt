package com.synctech.worksync.ui.screens.inventoryPanel

import com.synctech.worksync.ui.models.ItemUiModel

/**
 * Representa el estado de la pantalla de materiales en la interfaz de usuario.
 *
 * @param showLoadingIndicator Indica si se debe mostrar el indicador de carga.
 * @param inventory Lista de materiales representados por objetos de tipo ItemUiModel.
 */
data class InventoryState(
    val showLoadingIndicator: Boolean = false,
    val inventory: List<ItemUiModel> = emptyList(), // Lista de materiales
    val errorMessage: String? = null
)