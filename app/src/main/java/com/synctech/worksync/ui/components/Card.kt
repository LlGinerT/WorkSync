package com.synctech.worksync.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.theme.WorkSyncTheme

/**
 * Componente que representa una tarjeta elevada con información de un trabajo.
 * Se recomienda usar dentro de una cuadrícula o lista para mostrar múltiples trabajos.
 * @param id Identificador único del trabajo, que se mostrará en la tarjeta.
 * @see ElevatedCardsGrid
 */

@Composable
fun ElevatedCard(id: Int) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .size(width = 150.dp, height = 170.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Text(
                text = "ID Trabajo: $id",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Categoría del Trabajo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Dirección del Cliente",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Descripción del Trabajo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}

/**
 * Componente que muestra una cuadrícula de tarjetas elevadas.
 * Cada tarjeta representa un trabajo con un identificador único.
 * ### Características:
 * - Muestra **seis tarjetas** con IDs únicos.
 * - Distribución en **dos columnas** por fila.
 * - Se puede reutilizar para cualquier cantidad de tarjetas.
 * @see ElevatedCard
 */
@Composable
fun ElevatedCardsGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 tarjetas por fila
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(6) { index ->
            ElevatedCard(id = index + 1) // Crear 6 tarjetas, ID de 1 a 6
        }
    }
}

/**
 * Vista previa de la cuadrícula de tarjetas elevadas.
 */
@Preview(showBackground = true)
@Composable
fun ElevatedCardPreview() {
    WorkSyncTheme{ ElevatedCardsGrid()}
    //ElevatedCardsGrid()
}
