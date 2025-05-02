package com.synctech.worksync.ui.screens.userPanel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.data.cache.CacheActiveSessionRepository
import com.synctech.worksync.domain.useCases.session.LogoutUseCase
import com.synctech.worksync.domain.useCases.session.UpdateWorkSessionUseCase
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * ViewModel de la pantalla de panel de usuario.
 *
 * - Recupera la sesión activa desde caché.
 * - Gestiona el cronómetro.
 * - Cierra la sesión y actualiza los datos.
 *
 * @param cache Repositorio de sesión activa en caché.
 * @param updateWorkSessionUseCase Caso de uso para actualizar una sesión al cerrarla.
 * @param getActiveSessionUseCase Caso de uso que proporciona el usuario y la sesión activa.
 */
class UserPanelViewModel(
    private val cache: CacheActiveSessionRepository,
    private val updateWorkSessionUseCase: UpdateWorkSessionUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserPanelState())
    val uiState: StateFlow<UserPanelState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UserPanelUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var timerJob: Job? = null


    init {
        viewModelScope.launch {
            val sessionContext = cache.getActiveSessionContext()
            val user = sessionContext.user?.toUi()
            val session = sessionContext.session?.toUi()

            if (user != null && session != null) {
                val duration = ((System.currentTimeMillis() - session.startTime) / 1000).toInt()
                _uiState.update {
                    it.copy(
                        employee = user, workSession = session, secondsWorked = duration
                    )
                }
                startTimer()
            } else {
                Log.w("UserPanelViewModel", "No hay sesión activa en caché")
                //TODO: valorar emitir eventos de error y forzar logout
            }
        }
    }


    /**
     * Inicia el cronómetro de trabajo si no está corriendo.
     */
    private fun startTimer() {
        if (timerJob?.isActive == true) return
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _uiState.update {
                    it.copy(secondsWorked = it.secondsWorked + 1)
                }
            }
        }
    }

    /**
     * Detiene el cronómetro si está activo.
     */
    private fun stopTimer() {
        Log.i("UserPanelViewModel", "Deteniendo cronómetro")
        timerJob?.cancel()
    }

    /**
     * Cierra la sesión actual:
     * - Detiene el cronómetro.
     * - Actualiza la sesión en el repositorio.
     * - Limpia la caché y el estado.
     * - Emite evento para navegación o UI.
     */
    suspend fun logout() {
        _uiState.update { it.copy(isLoggingOut = true) }

        val result = logoutUseCase()

        if (result.isSuccess) {
            stopTimer()
            _uiState.value = UserPanelState()
            _eventFlow.emit(UserPanelUiEvent.LogoutSuccess)
        } else {
            _uiState.update { it.copy(isLoggingOut = false) }
            Log.e("UserPanelViewModel", "Error al cerrar sesión", result.exceptionOrNull())
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }
}