
package com.synctech.worksync.ui.screens.jobsPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.synctech.worksync.ui.components.JobCard

/**
 * Fondo de la pantalla de trabajos, con el color de fondo de la app.
 *
 * @param content Composable hijo que se mostrará sobre el fondo.
 */
@Composable
private fun JobsBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        content()
    }
}

/**
 * Pantalla principal que muestra la lista de trabajos disponibles.
 *
 * @param viewModel ViewModel que gestiona la lógica de trabajos.
 * @param navController Controlador de navegación para redirigir a la pantalla de detalle.
 * @param modifier Modificador opcional para ajustes de diseño.
 */


@Composable
fun JobScreen(
    viewModel: JobsViewModel, navController: NavController, modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    JobsBackground {
        when {
            uiState.showLoadingIndicator -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = uiState.errorMessage ?: "Error desconocido",
                        color = colorScheme.error,
                        style = typography.bodyLarge
                    )
                }
            }

            uiState.jobsList.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No hay trabajos disponibles.",
                        color = colorScheme.onBackground,
                        style = typography.bodyLarge
                    )
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.jobsList) { job ->
                        JobCard(job = job, onClick = {
                            navController.navigate("jobDetail/${job.jobId}")
                        })
                    }
                }
            }
        }
    }
}
/*
* actions ={
*   IconButton(onClick={
*       jobsVM.saveNewJob(
*
*
* */

