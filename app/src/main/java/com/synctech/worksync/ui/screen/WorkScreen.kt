package com.synctech.worksync.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.synctech.worksync.ui.components.ElevatedCardsGrid
import com.synctech.worksync.ui.models.WorkUIModel
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
    // Verifica si el usuario es administrador
    val isAdmin = uiState.user?.isAdmin ?: false
    WorkContent(uiState, isAdmin, modifier)
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
    isAdmin: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.showLoadingIndicator) {
            CircularProgressIndicator()
        } else {
            // Si no se está cargando, muestra los trabajos en una cuadrícula
            ElevatedCardsGrid(
                works = uiState.works
            )
        }

        // Mostrar un ícono de agregar si el usuario es administrador
        if (isAdmin) {
            FloatingActionButton(
                onClick = { /* Acción para agregar trabajo */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Trabajo")
            }
        }
    }
}

/**
 * @param WorkScreenPreview Vista previa de la pantalla de trabajos.
 */
/*@Preview(showBackground = true)
@Composable
fun WorkScreenPreview() {
    WorkSyncTheme {
        WorkContent(
            uiState = WorkState(showLoadingIndicator = false, works = listOf(
                WorkUIModel("Reparación ", "Cliente A", "Reparar", "Calle 123", "admin"),
                WorkUIModel("Instalación", "Cliente B", "Instalar WIFI", "Avenida 456", "user1"),
                WorkUIModel("Instalación", "Cliente C", "Instala Cable LAN", "Edificio 789", "user2")
            )
            ),
            isAdmin = true,  // Simulación de usuario administrador
            modifier = Modifier
        )
    }
}*/

