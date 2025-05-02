package com.synctech.worksync.ui.screens.userPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.data.cache.CacheUserSessionRepository
import com.synctech.worksync.domain.useCases.GetActiveSessionUseCase
import com.synctech.worksync.domain.useCases.UpdateWorkSessionUseCase
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class UserPanelViewModel(
    private val cache: CacheUserSessionRepository,
    private val updateWorkSessionUseCase: UpdateWorkSessionUseCase,
    private val getActiveSessionUseCase: GetActiveSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserPanelState())
    val uiState: StateFlow<UserPanelState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    private val sessionContext = getActiveSessionUseCase()

    init {
        //TODO: transformar a useCases
        val user = sessionContext.user?.toUi()
        val session = sessionContext.session?.toUi()

        if (user != null && session != null) {
            val duration = ((System.currentTimeMillis() - session.startTime) / 1000).toInt()
            _uiState.update {
                it.copy(
                    employee = user,
                    workSession = session,
                    secondsWorked = duration
                )
            }
            startTimer()
        }
    }

    private fun startTimer() {
        if (timerJob?.isActive == true) return
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _uiState.value =
                    _uiState.value.copy(secondsWorked = _uiState.value.secondsWorked + 1)
            }
        }
    }

    private fun stopTimer() {
        Log.i("UserPanelViewModel", "Deteniendo cronómetro")
        timerJob?.cancel()
    }

    suspend fun logout(): Result<Unit> {
        val user = sessionContext.user
        val session = sessionContext.session

        if (user != null && session != null) {
            Log.i("UserPanelViewModel", "Cerrando sesión para usuario ${user.userId}")
            _uiState.update { it.copy(isLoggingOut = true) }

            val result = updateWorkSessionUseCase(
                userId = user.userId,
                sessionId = session.sessionId,
                sessionStart = session.startTime,
                sessionEnd = System.currentTimeMillis()
            )

            if (result.isSuccess) {
                Log.i("UserPanelViewModel", "Sesión guardada correctamente, limpiando caché")
                stopTimer()
                cache.clearAll()
                _uiState.value = UserPanelState() // Limpia toda la UI
            } else {
                Log.e("UserPanelViewModel", "Error al guardar sesión al cerrar")
                _uiState.update { it.copy(isLoggingOut = false) }
            }

            return result
        } else {
            Log.w("UserPanelViewModel", "Logout fallido: usuario o sesión no disponibles en caché")
            // TODO: Mostrar snackbar o alerta y excepcion personalizada
            return Result.failure(Exception("Error al cerrar sesión"))
        }
    }
}