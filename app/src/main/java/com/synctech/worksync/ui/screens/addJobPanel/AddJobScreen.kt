package com.synctech.worksync.ui.screens.addJobPanel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddJobScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit,
    viewModel: AddJobViewModel
){

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is AddJobUiEvent.JobSaved -> {
                    onSave()
                }

                is AddJobUiEvent.JobCancelled -> {
                    onCancel()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nuevo Trabajo", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.jobName,
            onValueChange = viewModel::onJobNameChanged,
            label = { Text("Nombre del Trabajo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.clientName,
            onValueChange = viewModel::onClientNameChanged,
            label = { Text("Nombre del Cliente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.address,
            onValueChange = viewModel::onAddressChanged,
            label = { Text("Dirección") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = viewModel::onDescriptionChanged,
            label = { Text("Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            maxLines = 5
        )
        OutlinedTextField(
            value = state.assignedTo,
            onValueChange = viewModel::onAssignedToChanged,
            label = { Text("Asignado a") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            maxLines = 5
        )
        Row(modifier = Modifier.padding(10.dp)){
            Button(onClick = { viewModel.cancel() }, modifier = Modifier.weight(1f).padding(end = 8.dp)) { Text("Cancelar") }
            Button(onClick = { viewModel.save()}, modifier = Modifier.weight(1f)) { Text("Guardar") }
        }
    }
}
