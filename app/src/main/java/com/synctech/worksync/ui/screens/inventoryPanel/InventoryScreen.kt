package com.synctech.worksync.ui.screens.inventoryPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
 * Pantalla principal que maneja la vista de los materiales.
 *
 * @param viewModel El ViewModel que proporciona el estado de la UI.
 * @param modifier Modificador para aplicar ajustes al diseño del Composable.
 */
@Composable
fun MaterialScreen(
    viewModel: InventoryViewModel, navController: NavController, modifier: Modifier = Modifier
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
                            text = "No hay trabajos disponibles.",
                            color = colorScheme.onBackground,
                            style = typography.bodyLarge
                        )
                    }
                } else {
                    MaterialList(uiState.inventory)
                }
            }
        }
    }
}

/**
 * Lista de materiales mostrada en la pantalla.
 *
 * @param materials Lista de objetos ItemUiModel que contienen los datos de los materiales.
 */
@Composable
fun MaterialList(
    materials: List<ItemUiModel>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            // Cabecera de la lista
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.primary.copy(alpha = 0.1f))
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Columna para el encabezado de "ID"
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ID",
                        style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                // Columna para el encabezado de "Nombre"
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Nombre",
                        style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                // Columna para el encabezado de "Precio"
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Precio",
                        style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
                // Columna para el encabezado de "Cantidad"
                Column(
                    modifier = Modifier.weight(2f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cantidad",
                        style = typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
        items(materials) { material ->
            // Elementos de la lista
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
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
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = material.itemId.toString(), style = typography.bodyMedium)
                    }

                    // Columna para el Nombre
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = material.name, style = typography.bodyMedium)
                    }

                    // Columna para el Precio
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${material.precio} €", style = typography.bodyMedium)
                    }

                    // Columna para la Cantidad
                    Column(
                        modifier = Modifier.weight(2f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = material.cantidad.toString(), style = typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialScreenPreview() {
    val dummyMaterials = listOf(
        ItemUiModel(itemId = 1, name = "Cable Wifi", precio = 100.50, cantidad = 45),
        ItemUiModel(itemId = 2, name = "Adaptador RJ45", precio = 150.90, cantidad = 30),
        ItemUiModel(itemId = 3, name = "Módem", precio = 160.10, cantidad = 55)
    )

    InventoryBackground {
        MaterialList(materials = dummyMaterials)
    }
}

