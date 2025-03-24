package com.synctech.worksync

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.synctech.worksync.data.testData.MockUserAuthRepository
import com.synctech.worksync.data.testData.MockWorkSessionRepository
import com.synctech.worksync.data.testData.MockWorkersRepository
import com.synctech.worksync.domain.useCases.AuthUserUseCase
import com.synctech.worksync.domain.useCases.SaveWorkSessionUseCase
import com.synctech.worksync.ui.screens.login.LoginScreen
import com.synctech.worksync.ui.screens.login.LoginViewModel
import com.synctech.worksync.ui.session.SessionViewModel
import com.synctech.worksync.ui.theme.WorkSyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Creado en el MainActivity con fines de probar el login en el emulador, se puede eliminar
        // Mock repos
        val userAuthRepo = MockUserAuthRepository()
        val workersRepo = MockWorkersRepository()
        val workSessionRepo = MockWorkSessionRepository()

        // UseCase + ViewModels
        val authUserUseCase = AuthUserUseCase(userAuthRepo, workersRepo)
        val saveWorkSessionUseCase = SaveWorkSessionUseCase(workSessionRepo)
        val loginViewModel = LoginViewModel(authUserUseCase)
        val sessionViewModel = SessionViewModel(saveWorkSessionUseCase)

        setContent {
            WorkSyncTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        loginViewModel = loginViewModel,
                        sessionViewModel = sessionViewModel,
                        onLoginSuccess = {
                            // Aquí solo logueamos para test
                            Log.i("MainActivity", "Login confirmado desde MainActivity")
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

