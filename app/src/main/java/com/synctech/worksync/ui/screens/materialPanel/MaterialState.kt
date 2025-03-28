package com.synctech.worksync.ui.screens.materialPanel

import com.synctech.worksync.ui.models.MaterialUiModel


data class MaterialState(
    val showLoadingIndicator: Boolean = false,
    val materials: List<MaterialUiModel> = emptyList() // Lista de materiales
)