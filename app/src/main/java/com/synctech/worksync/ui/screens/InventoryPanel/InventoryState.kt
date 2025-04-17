package com.synctech.worksync.ui.screens.InventoryPanel

import com.synctech.worksync.ui.models.MaterialUiModel

/**
 * Representa el estado de la pantalla de materiales en la interfaz de usuario.
 *
 * @param showLoadingIndicator Indica si se debe mostrar el indicador de carga.
 * @param materials Lista de materiales representados por objetos de tipo MaterialUiModel.
 */
data class InventoryState(
    val showLoadingIndicator: Boolean = false,
    val materials: List<MaterialUiModel> = emptyList() // Lista de materiales
)