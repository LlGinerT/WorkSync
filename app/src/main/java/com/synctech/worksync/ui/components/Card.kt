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

/**
 * Componente que representa una tarjeta elevada con información de un trabajo.
 *
 * @param id Identificador único del trabajo, que se mostrará en la tarjeta.
 *
 * ### Uso:
 * ```kotlin
 * ElevatedCardExample(id = 1)
 * ```
 * Se recomienda usar dentro de una cuadrícula o lista para mostrar múltiples trabajos.
 *
 * @see ElevatedCardsGrid
 */

@Composable
fun ElevatedCardExample(id: Int) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer // Usamos `primaryContainer` que se definió en el tema
        ),
        modifier = Modifier
            .size(width = 150.dp, height = 170.dp) // Tamaño ajustado para 2 tarjetas por fila
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp) // Espaciado uniforme
        ) {
            // Usamos el color `onPrimary` para el texto, adecuado sobre un fondo claro o primario
            Text(
                text = "ID Trabajo: $id",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary, // Color para el texto sobre el color primario
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Categoría del Trabajo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary, // Color de texto según el tema
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Dirección del Cliente",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary, // Aseguramos que el texto se vea bien
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Descripción del Trabajo",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary, // Color adecuado para el texto
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}

/**
 * Componente que muestra una cuadrícula de tarjetas elevadas.
 * Cada tarjeta representa un trabajo con un identificador único.
 *
 * ### Características:
 * - Muestra **seis tarjetas** con IDs únicos.
 * - Distribución en **dos columnas** por fila.
 * - Se puede reutilizar para cualquier cantidad de tarjetas.
 *
 * @see ElevatedCardExample
 */

@Composable
fun ElevatedCardsGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 tarjetas por fila
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Aumentamos el padding superior para mover las tarjetas hacia abajo
        contentPadding = PaddingValues(16.dp), // Espaciado general entre las tarjetas
        horizontalArrangement = Arrangement.spacedBy(24.dp), // Más espacio horizontal entre las tarjetas
        verticalArrangement = Arrangement.spacedBy(24.dp) // Más espacio vertical entre las tarjetas
    ) {
        items(6) { index ->
            ElevatedCardExample(id = index + 1) // Crear 6 tarjetas, ID de 1 a 6
        }
    }
}

/**
 * Vista previa de la cuadrícula de tarjetas elevadas.
 */

@Preview(showBackground = true)
@Composable
fun ElevatedCardPreview() {
    ElevatedCardsGrid()
}
