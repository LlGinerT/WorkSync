package com.synctech.worksync.ui.viewmodel

import com.synctech.worksync.ui.models.WorkUIModel

/**
 * Representa el estado de la UI para mostrar la lista de trabajos y el indicador de carga.
 *
 * @param showLoadingIndicator Indica si se debe mostrar o no el indicador de carga.
 * @param works La lista de trabajos que se mostrarán en la UI.
 */
data class WorkState(
    val showLoadingIndicator: Boolean = false,  // Indica si se muestra el indicador de carga.
    val works: List<WorkUIModel> = emptyList()  // Lista de trabajos que se mostrarán en la UI.
    //val title: String = ""  // Comentado por ahora, puede ser usado para un título en la UI.
)
