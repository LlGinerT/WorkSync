package com.synctech.worksync.ui.screens.jobsPanel

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditJobView(navController:NavController, jobViewModel:JobVieModel, idDoc:String) {
    LaunchedEffect(Unit) {
        jobViewModel.getJobById(idDoc)
    }
    val state = jobViewModel.state
    Text(text = state.jobName)
}

Scaffold(
topBar = {
    TopAppBar(
        title = { Text(text = "Editar Trabajo") },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                jobViewModel.updateJob(idDoc){
                    navController.popBackStack()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Edit, contentDescription = "Editar",

                )
            }
        }
    )
}
) { pad ->
    Column(
        modifier = Modifier
            .padding(pad)
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.jobName,
            onValueChange = { jobsViewModel.onValue(it,"jobName")},
            label = { Text("Nombre del Trabajo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.clientName,
            onValueChange = { jobViewModel.onValue(it, "clientName") },
            label = { Text("Nombre del Cliente") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.address,
            onValueChange = { jobViewModel.onValue( it,"address" },
            label = { Text("Dirección") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = state.description,
            onValueChange = { jobViewModel.onValue( it,"description" },
            label = { Text("Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            maxLines = 5
        )
    }
}
}
