package com.synctech.worksync.ui.screens.InventoryPanel

import com.synctech.worksync.ui.models.ItemUiModel

/**
 * Representa el estado de la pantalla de materiales en la interfaz de usuario.
 *
 * @param showLoadingIndicator Indica si se debe mostrar el indicador de carga.
 * @param materials Lista de materiales representados por objetos de tipo ItemUiModel.
 */
data class InventoryState(
    val showLoadingIndicator: Boolean = false,
    val materials: List<ItemUiModel> = emptyList() // Lista de materiales
)