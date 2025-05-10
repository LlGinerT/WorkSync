package com.synctech.worksync.ui.screens.jobsPanel

import com.synctech.worksync.ui.models.JobUiModel

/**
 * Representa el estado de la UI para mostrar la lista de trabajos y el indicador de carga.
 *
 * @param showLoadingIndicator Indica si se debe mostrar o no el indicador de carga.
 * @param jobsList La lista completa de trabajos obtenidos del repositorio.
 * @param filteredJobs Lista de trabajos filtrados según el usuario actual (si no es admin, solo verá sus trabajos asignados).
 */
data class JobsState(
    val showLoadingIndicator: Boolean = false,
    val jobsList: List<JobUiModel> = emptyList(), // Lista de todos los trabajos
    val filteredJobs: List<JobUiModel> = emptyList(),
    val errorMessage: String? = null
// filteredJobs solo sirve tener 2 listas, si aplicamos filtros
// dinamicos con una sola lista sirve para si es admin o no
)
