package com.synctech.worksync.ui.viewmodel

import com.synctech.worksync.domain.models.User
import com.synctech.worksync.ui.models.WorkUIModel

/**
 * Representa el estado de la UI para mostrar la lista de trabajos y el indicador de carga.
 *
 * @param showLoadingIndicator Indica si se debe mostrar o no el indicador de carga.
 * @param works La lista de trabajos que se mostrarán en la UI.
 * @param user El usuario actual con su rol (admin o no).
 */
data class WorkState(
    val showLoadingIndicator: Boolean = false,
    val works: List<WorkUIModel> = emptyList(),// Lista de trabajos que se mostrarán en la UI.
    val user: User? = null // Usuario actual, puede ser admin o no.
    //val title: String = ""  // Comentado por ahora, puede ser usado para un título en la UI.
)
