package com.synctech.worksync.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.synctech.worksync.ui.components.ElevatedCardsGrid
import com.synctech.worksync.ui.theme.WorkSyncTheme
import com.synctech.worksync.ui.viewmodel.WorkState
import com.synctech.worksync.ui.viewmodel.WorkViewModel
import com.synctech.worksync.ui.models.toUI

/**
 * Pantalla principal que muestra los trabajos.
 *
 * @param viewModel El ViewModel que contiene la lógica y estado para gestionar los trabajos.
 * @param modifier Modificador opcional para personalizar el diseño de la pantalla.
 */
@Composable
fun WorkScreen(
    viewModel: WorkViewModel,
    modifier: Modifier = Modifier
) {
    // Recoge el estado del ViewModel y se asegura de que se actualice con el ciclo de vida
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WorkContent(uiState, modifier)
}

/**
 * Contenido de la pantalla de trabajos.
 *
 * @param uiState El estado de la interfaz de usuario que contiene los trabajos y el indicador de carga.
 * @param modifier Modificador opcional para personalizar el diseño de los componentes dentro de la pantalla.
 */
@Composable
fun WorkContent(
    uiState: WorkState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Si se está cargando, muestra el indicador de progreso
        if (uiState.showLoadingIndicator) {
            CircularProgressIndicator()
        } else {
            // Si no se está cargando, muestra los trabajos en una cuadrícula
            ElevatedCardsGrid(
                works = uiState.works
            )
        }
    }
}

/**
 * @param WorkScreenPreview Vista previa de la pantalla de trabajos.
 */
@Preview(showBackground = true)
@Composable
fun WorkScreenPreview() {
    WorkSyncTheme {
        WorkContent(
            uiState = WorkState(showLoadingIndicator = false, works = emptyList())
        )
    }
}
