package com.synctech.worksync.ui.screens.materialPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.models.MaterialUiModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

/**
 * Pantalla principal que maneja la vista de los materiales.
 *
 * @param viewModel El ViewModel que proporciona el estado de la UI.
 * @param modifier Modificador para aplicar ajustes al diseño del Composable.
 */
@Composable
fun MaterialScreen(
    viewModel: MaterialViewModel,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val uiState by viewModel.uiState.collectAsState()

    MaterialContent(uiState, modifier, backgroundColor)
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
    uiState: MaterialState,
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
 * @param materials Lista de objetos MaterialUiModel que contienen los datos de los materiales.
 */
@Composable
fun MaterialList(
    materials: List<MaterialUiModel>
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
                    .background(colorScheme.primary.copy(alpha = 0.1f)) //factor de peso,como debe distribuirse el espacio
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
                        text = material.materialId.toString(),
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

/**
 * Vista previa de la pantalla de materiales.
 */
@Preview(showBackground = true)
@Composable
fun MaterialScreenPreview() {
    WorkSyncTheme {
        MaterialContent(
            uiState = MaterialState(
                showLoadingIndicator = false, materials = listOf(
                    MaterialUiModel(1, "Cable de red", 10.0, 200),
                    MaterialUiModel(2, "Router WiFi", 50.0, 25),
                    MaterialUiModel(3, "Switch de red", 120.0, 15),
                    MaterialUiModel(4, "Adaptador RJ45", 5.0, 300),
                    MaterialUiModel(5, "Módem", 80.0, 10)
                )),
            modifier = Modifier,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

//Codigo usando Text y for each para simplificar codigo.
//@Composable
//fun SimpleMaterialList(materials: List<MaterialUiModel>) {
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

