package com.synctech.worksync.ui.screens.InventoryPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.synctech.worksync.ui.models.ItemUiModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

/**
 * Pantalla principal que maneja la vista de los materiales.
 *
 * @param viewModel El ViewModel que proporciona el estado de la UI.
 * @param modifier Modificador para aplicar ajustes al diseño del Composable.
 */
@Composable
fun MaterialScreen(
    viewModel: InventoryViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val uiState by viewModel.uiState.collectAsState()

    // Aquí pasamos el estado a la función MaterialContent
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        MaterialContent(
            uiState = uiState,
            modifier = modifier,
            backgroundColor = backgroundColor ) // Asegúrate de pasar uiState correctamente

        // Botón para regresar a WorkScreen
        IconButton(
            onClick = { navController.navigateUp() }, // Regresa a la pantalla anterior
            modifier = Modifier
                .align(Alignment.TopStart) // Alineamos el ícono en la esquina superior izquierda
                .padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
        }
    }
}


/**
 * Contenido de la pantalla de materiales.
 *
 * @param uiState Estado actual de la pantalla con la lista de materiales y el indicador de carga.
 * @param modifier Modificador opcional para el diseño.
 * @param backgroundColor Color de fondo de la pantalla.
 */
@Composable
fun MaterialContent(
    uiState: InventoryState,
    modifier: Modifier = Modifier,
    backgroundColor: Color
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.showLoadingIndicator) {
            CircularProgressIndicator(modifier = Modifier.size(60.dp))
        } else {
            MaterialList(materials = uiState.materials)
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
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
                Text(
                    text = "ID",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Nombre",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "Precio",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Cantidad",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
            }
        }
        items(materials) { material ->
            // Elementos de la lista
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = material.itemId.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = material.name,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = "$${material.precio}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = material.cantidad.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialScreenPreview() {
    WorkSyncTheme {
        MaterialContent(
            uiState = InventoryState(
                showLoadingIndicator = false, materials = listOf(
                    ItemUiModel(1, "Cable de red", 10.0, 200),
                    ItemUiModel(2, "Router WiFi", 50.0, 25),
                    ItemUiModel(3, "Switch de red", 120.0, 15),
                    ItemUiModel(4, "Adaptador RJ45", 5.0, 300),
                    ItemUiModel(5, "Módem", 80.0, 10)
                )),
            modifier = Modifier,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

//Codigo usando Text y for each para simplificar codigo.
//@Composable
//fun SimpleMaterialList(materials: List<ItemUiModel>) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        // Cabecera
//        Text(
//            text = "ID | Nombre | Precio | Cantidad",
//            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//        )
//
//        // Lista simple
//        materials.forEach { material ->
//            Text(
//                text = "${material.materialId} | ${material.name} | $${material.precio} | ${material.cantidad}",
//                style = MaterialTheme.typography.bodyMedium
//            )
//        }
//    }
//}

//SimpleMaterialList(materials = uiState.materials)

