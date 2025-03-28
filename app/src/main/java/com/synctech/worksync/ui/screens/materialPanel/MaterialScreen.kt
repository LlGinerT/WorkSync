package com.synctech.worksync.ui.screens.materialPanel



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.models.MaterialUiModel

import com.synctech.worksync.ui.theme.WorkSyncTheme


@Composable
fun MaterialScreen(
    viewModel: MaterialViewModel,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val uiState by viewModel.uiState.collectAsState()

    MaterialContent(uiState, modifier, backgroundColor)
}

@Composable
fun MaterialContent(
    uiState: MaterialState,
    modifier: Modifier = Modifier,
    backgroundColor: Color
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
            MaterialList(materials = uiState.materials)
        }
    }
}

@Composable
fun MaterialList(
    materials: List<MaterialUiModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "ID",
                    modifier = Modifier.weight(1f))
                Text(text = "Nombre",
                    modifier = Modifier.weight(2f))
                Text(text = "Precio",
                    modifier = Modifier.weight(1f))
                Text(text = "Cantidad",
                    modifier = Modifier.weight(1f))
            }
        }
        items(materials) { material ->
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = material.materialId.toString(),
                    modifier = Modifier.weight(1f))
                Text(text = material.name,
                    modifier = Modifier.weight(2f))
                Text(text = "${material.precio}",
                    modifier = Modifier.weight(1f))
                Text(text = material.cantidad.toString(),
                    modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialScreenPreview() {
    WorkSyncTheme {
        MaterialContent(
            uiState = MaterialState(
                showLoadingIndicator = false, materials = listOf(
                MaterialUiModel( 1,"Cable de red", 10.0, 200),
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
