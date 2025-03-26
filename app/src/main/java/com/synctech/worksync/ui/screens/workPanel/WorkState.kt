package com.synctech.worksync.ui.screens.workPanel

import com.synctech.worksync.domain.models.User
import com.synctech.worksync.ui.models.WorkUIModel

/**
 * Representa el estado de la UI para mostrar la lista de trabajos y el indicador de carga.
 *
 * @param showLoadingIndicator Indica si se debe mostrar o no el indicador de carga.
 * @param works La lista completa de trabajos obtenidos del repositorio.
 * @param user El usuario actual con su rol (admin o no).
 * @param filteredWorks Lista de trabajos filtrados según el usuario actual (si no es admin, solo verá sus trabajos asignados).
 */
data class WorkState(
    val showLoadingIndicator: Boolean = false,
    val works: List<WorkUIModel> = emptyList(), // Lista de todos los trabajos
    val user: User? = null, // Usuario actual (puede ser admin o técnico)
    val filteredWorks: List<WorkUIModel> = emptyList() // Solo los trabajos asignados al usuario si no es admin
)
