package com.synctech.worksync.ui.screens.inventoryPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.synctech.worksync.ui.models.ItemUiModel

/**
 * Fondo con color por defecto de la aplicación para la pantalla de inventario.
 *
 * @param content Contenido visual que se mostrará sobre el fondo.
 */
@Composable
private fun InventoryBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        content()
    }
}

/**
 * Pantalla principal de visualización de materiales del inventario.
 *
 * @param viewModel ViewModel que proporciona el estado y operaciones.
 * @param navController Controlador de navegación (sin uso por ahora).
 * @param modifier Modificador de layout externo.
 */
@Composable
fun MaterialScreen(
    viewModel: InventoryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    InventoryBackground {
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

            else -> {
                if (uiState.inventory.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No hay materiales disponibles.",
                            color = colorScheme.onBackground,
                            style = typography.bodyLarge
                        )
                    }
                } else {
                    MaterialList(
                        materials = uiState.inventory,
                        onItemClick = { /* preparar para editar más adelante */ }
                    )
                }
            }
        }
    }
}

/**
 * Lista visual de materiales del inventario.
 *
 * @param materials Lista de materiales en formato UI.
 * @param onItemClick Acción al pulsar un material (por ahora sin uso).
 */
@Composable
fun MaterialList(
    materials: List<ItemUiModel>,
    onItemClick: (ItemUiModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.primary.copy(alpha = 0.1f))
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Encabezado de la lista con los títulos: ID, Nombre, Precio y Cantidad
                Text(
                    "ID",
                    style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(2f)
                )
                Text(
                    "Nombre",
                    style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(2f)
                )
                Text(
                    "Precio",
                    style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(2f)
                )
                Text(
                    "Cantidad",
                    style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(2f)
                )
            }
        }

        items(materials) { material ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onItemClick(material) },
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                shape = shapes.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    // Columna para el ID
                    Text(
                        text = material.itemId.toString(),
                        style = typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )

                    // Columna para el Nombre
                    Text(
                        text = material.name,
                        style = typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )

                    // Columna para el Precio
                    Text(
                        text = "${material.precio} €",
                        style = typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )

                    // Columna para la Cantidad
                    Text(
                        text = material.cantidad.toString(),
                        style = typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}
