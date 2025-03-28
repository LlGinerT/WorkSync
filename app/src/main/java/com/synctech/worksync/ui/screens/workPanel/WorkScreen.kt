package com.synctech.worksync.ui.screens.workPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.components.WorkCard
import com.synctech.worksync.ui.models.WorkUIModel
import com.synctech.worksync.ui.theme.WorkSyncTheme
/**
 * Componente que envuelve el contenido con un fondo de color primario.
 *
 * @param content El contenido composable que se va a mostrar dentro del fondo.
 */
@Composable
fun WorkBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        content()
    }
}
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
    // Definicion del color que se va a utilizar en toda la pantalla
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val cardColor = MaterialTheme.colorScheme.secondaryContainer
    // Recoge el estado del ViewModel y se asegura de que se actualice con el ciclo de vida
    val uiState by viewModel.uiState.collectAsState()
    // Verifica si el usuario es administrador
    val isAdmin = uiState.user?.isAdmin ?: false
    WorkContent(uiState, isAdmin, modifier,backgroundColor, cardColor )
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
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cardColor: Color
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.showLoadingIndicator) {
            CircularProgressIndicator()
        } else {
            // Si no se está cargando, muestra los trabajos en una cuadrícula
            ElevatedCardsGrid(
                works = uiState.works,
                cardColor = cardColor

            )
        }

        // Mostrar un ícono de agregar si el usuario es administrador, esto ira en el scaffold.
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
@Composable
fun ElevatedCardsGrid(
    works: List<WorkUIModel>,
    cardColor: Color
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(works) { work ->
            WorkCard(work = work, onClick = {}, cardColor = cardColor)// Pasa cada trabajo individual a la tarjeta
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
            uiState = WorkState(showLoadingIndicator = false, works = listOf(
                WorkUIModel("Reparación ", "Cliente A", "Reparar", "Calle 123", "admin"),
                WorkUIModel("Instalación", "Cliente B", "Instalar WIFI", "Avenida 456", "user1"),
                WorkUIModel("Instalación", "Cliente C", "Instala Cable LAN", "Edificio 789", "user2"),
                WorkUIModel("Reparación ", "Cliente D", "Reparar", "Calle 123", "admin"),
                WorkUIModel("Instalación", "Cliente E", "Instalar WIFI", "Avenida 321", "admin"),
                WorkUIModel("Instalación", "Cliente F", "InstalaWIFI", "Avenida 654", "admin")
                )
            ),
            isAdmin = true,  // Simulación de usuario administrador
            modifier = Modifier,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            cardColor = MaterialTheme.colorScheme.secondaryContainer
        )
    }
}

/**
 *  ElevatedCardPreview Vista previa de la tarjeta elevada con una cuadrícula de trabajos.
 */
@Preview(showBackground = true)
@Composable
fun ElevatedCardPreview() {
    // Definición de los colores que se van a usar en la vista previa
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val cardColor = MaterialTheme.colorScheme.secondaryContainer
    WorkSyncTheme {
        ElevatedCardsGrid(
            works = listOf(
                WorkUIModel("Trabajo 1", "Cliente 1", "Descripción 1", "Dirección 1", "admin"),
                WorkUIModel("Trabajo 2", "Cliente 2", "Descripción 2", "Dirección 2", ""),
                WorkUIModel("Trabajo 3", "Cliente 3", "Descripción 3", "Dirección 3", ""),
                WorkUIModel("Trabajo 4", "Cliente 4", "Descripción 4", "Dirección 4", ""),
                WorkUIModel("Trabajo 5", "Cliente 5", "Descripción 5", "Dirección 5", ""),
                WorkUIModel("Trabajo 6", "Cliente 6", "Descripción 6", "Dirección 6", "")

            ),
            cardColor = cardColor // Pasamos el color de la tarjeta
        )
    }
}
