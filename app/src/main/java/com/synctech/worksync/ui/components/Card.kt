package com.synctech.worksync.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.synctech.worksync.ui.models.WorkUIModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

@Composable
fun ElevatedCard(work: WorkUIModel) {
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
                text = "Trabajo: ${work.jobName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Cliente: ${work.clientName}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Descripción: ${work.description}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Dirección: ${work.address}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

        }
    }
}

@Composable
fun ElevatedCardsGrid(
    works: List<WorkUIModel> // Recibe una lista de trabajos
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(works) { work ->
            ElevatedCard(work = work) // Pasa cada trabajo individual
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElevatedCardPreview() {
    WorkSyncTheme {
        ElevatedCardsGrid(
            works = listOf(
                WorkUIModel("Trabajo 1", "Cliente 1", "Descripción 1", "Dirección 1"),
                WorkUIModel("Trabajo 2", "Cliente 2", "Descripción 2", "Dirección 2"),
                WorkUIModel("Trabajo 3", "Cliente 3", "Descripción 3", "Dirección 3"),
                WorkUIModel("Trabajo 4", "Cliente 4", "Descripción 4", "Dirección 4"),
                WorkUIModel("Trabajo 5", "Cliente 5", "Descripción 5", "Dirección 5"),
                WorkUIModel("Trabajo 6", "Cliente 6", "Descripción 6", "Dirección 6")
            )
        )
    }
}