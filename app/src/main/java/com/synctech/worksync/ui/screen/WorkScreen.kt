package com.synctech.worksync.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.synctech.worksync.ui.components.ElevatedCardsGrid
import com.synctech.worksync.ui.theme.WorkSyncTheme
import com.synctech.worksync.ui.viewmodel.WorkState
import com.synctech.worksync.ui.viewmodel.WorkViewModel
import com.synctech.worksync.ui.models.toUI

@Composable
fun WorkScreen(
    viewModel: WorkViewModel,
    modifier: Modifier = Modifier
) {
    // Recoge el estado del ViewModel y se asegura de que se actualice con el ciclo de vida
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    WorkContent(uiState, modifier)
}

@Composable
fun WorkContent(
    uiState: WorkState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.showLoadingIndicator) {
            CircularProgressIndicator()
        } else {

            ElevatedCardsGrid(
                works = uiState.works
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WorkScreenPreview() {
    WorkSyncTheme {
        WorkContent(
            uiState = WorkState(showLoadingIndicator = false, works = emptyList())
        )
    }
}
