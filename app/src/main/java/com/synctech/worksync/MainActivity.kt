package com.synctech.worksync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.synctech.worksync.data.testData.FakeWorkDataRepository
import com.synctech.worksync.data.testData.MockMaterialDataRepository
import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.useCases.GetMaterialUseCase
import com.synctech.worksync.domain.useCases.GetWorkUseCase
import com.synctech.worksync.ui.screens.materialPanel.MaterialScreen
import com.synctech.worksync.ui.screens.materialPanel.MaterialViewModel
import com.synctech.worksync.ui.screens.workPanel.WorkScreen
import com.synctech.worksync.ui.screens.workPanel.WorkViewModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

class MainActivity : ComponentActivity() {
    private val getWorkUseCase = GetWorkUseCase(FakeWorkDataRepository())
    private val currentUser =
        User(userId = "78", username = "Andrés Sanz", isAdmin = true) // Usuario actual
    private val workViewModel = WorkViewModel(getWorkUseCase, currentUser)

    private val getMaterialUseCase = GetMaterialUseCase(MockMaterialDataRepository())
    private val materialViewModel = MaterialViewModel(getMaterialUseCase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkSyncTheme {
                // Llamamos a la función que configura la navegación
                SetupNavGraph()
            }
        }
    }

    // Setup de la Navegación
    @Composable
    fun SetupNavGraph() {
        val navController = rememberNavController()

        // NavHost que maneja la navegación
        NavHost(navController = navController, startDestination = "workScreen") {
            // Pantalla de trabajo
            composable("workScreen") {
                WorkScreen(
                    viewModel = workViewModel,
                    navController = navController, // Pasamos el navController para navegación
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Pantalla de materiales
            composable("materialScreen") {
                MaterialScreen(
                    viewModel = materialViewModel,
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}



            // Agregar más pantallas aquí de acuerdo a tu flujo de navegación




