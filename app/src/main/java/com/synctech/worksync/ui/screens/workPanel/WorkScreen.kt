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
import androidx.navigation.NavController
import com.synctech.worksync.ui.components.WorkCard
import com.synctech.worksync.ui.models.WorkUIModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

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

@Composable
fun WorkScreen(
    viewModel: WorkViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer
    val cardColor = MaterialTheme.colorScheme.secondaryContainer
    val uiState by viewModel.uiState.collectAsState()
    val isAdmin = uiState.user?.isAdmin ?: false

    WorkContent(
        uiState = uiState,
        isAdmin = isAdmin,
        modifier = modifier,
        backgroundColor = backgroundColor,
        cardColor = cardColor,
        navController = navController
    )
}

@Composable
fun WorkContent(
    uiState: WorkState,
    isAdmin: Boolean,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    cardColor: Color,
    navController: NavController
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
            ElevatedCardsGrid(
                works = uiState.works,
                cardColor = cardColor,
                onWorkClick = { work ->
                    navController.navigate(
                        "workDetail/${work.jobName}/${work.clientName}/${work.description}/${work.address}/${work.assignedTo}"
                    )
                }
            )
        }

        // Botón para navegar a MaterialScreen
        FloatingActionButton(
            onClick = {
                navController.navigate("materialScreen") // Navegar a MaterialScreen
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Ver Materiales")
        }


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
    cardColor: Color,
    onWorkClick: (WorkUIModel) -> Unit
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
            WorkCard(
                work = work,
                onClick = { onWorkClick(work) },
                cardColor = cardColor
            )
        }
    }
}
