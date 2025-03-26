package com.synctech.worksync.ui.session

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.models.WorkerDomainModel
import com.synctech.worksync.domain.useCases.SaveWorkSessionUseCase
import com.synctech.worksync.ui.models.WorkerUiModel
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * ViewModel compartido que gestiona la sesión activa del usuario autenticado.
 *
 * Este ViewModel:
 * - Almacena el usuario logueado.
 * - Inicia y gestiona un cronómetro que cuenta los segundos trabajados.
 * - Guarda una sesión de trabajo al cerrar sesión.
 *
 * @property saveWorkSessionUseCase Caso de uso para guardar una sesión de trabajo al finalizar.
 */
class SessionViewModel(
    private val saveWorkSessionUseCase: SaveWorkSessionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SessionState())
    val state: StateFlow<SessionState> = _state.asStateFlow()

    val uiWorker: WorkerUiModel? get() = _state.value.domainWorker?.toUi()

    // Job que representa la corutina activa del cronómetro
    private var timerJob: Job? = null

    /**
     * Inicia el cronómetro si no está ya activo y almacena un TimeStamp.
     */
    private fun startTimer() {
        if (timerJob?.isActive == true) return
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _state.value = _state.value.copy(
                    secondsWorked = _state.value.secondsWorked + 1
                )
                Log.i("SessionViewModel", "Crono: ${getFormattedWorkedTime()}")
            }
        }
    }

    /**
     * Detiene el cronómetro activo, si lo hay.
     */
    private fun stopTimer() {
        timerJob?.cancel()
    }

    /**
     * Establece el trabajador autenticado en sesión, almacena el tiempo de inicio
     * y comienza el cronómetro.
     *
     * @param workerDomainModel Modelo del trabajador.
     */
    fun setWorker(workerDomainModel: WorkerDomainModel) {
        _state.value = _state.value.copy(
            domainWorker = workerDomainModel,
            sessionStart = System.currentTimeMillis(),
            secondsWorked = 0
        )
        startTimer()
        Log.d("SessionViewModel","TimeStamp: ${getFormattedStartedSession()}")
    }

    /**
     * Finaliza la sesión del usuario:
     * - Detiene el cronómetro.
     * - Guarda la sesión de trabajo mediante el caso de uso.
     * - Limpia el estado del ViewModel.
     */
    fun logout() {
        val end = System.currentTimeMillis()
        val start = _state.value.sessionStart
        val user = _state.value.domainWorker
        if (user != null && start != null) {
            viewModelScope.launch {
                try {
                    saveWorkSessionUseCase(user.userId, start, end)
                    Log.i("SessionViewModel","Sesion guardada en ViewModel.")
                } catch (e: Exception) {
                    Log.e("SessionViewModel", "Error al guardar la sesión de trabajo", e)
                }
            }
        } else {
            Log.w(
                "SessionViewModel",
                "No se pudo guardar la sesión: datos incompletos (user=$user, start=$start)"
            )
            // TODO: Implentar sistema de pila para intentarlo mas adelante.
        }

        stopTimer()
        _state.value = SessionState() //Función recursiva que reinicia el estado completo
    }

    /**
     *  Devuelve el tiempo trabajado formateado.
     *
     *  @return Tiempo trabajado en formato HH:MM:SS
     */
    fun getFormattedWorkedTime(): String {
        val totalSeconds = _state.value.secondsWorked
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

    /**
     * Devuelve el TimeStamp formateado
     *
     * @return TimeStamp con formato:dd/MM/yyyy HH:mm o TimeStamp no disponible
     */
    fun getFormattedStartedSession(): String {
        val sessionStartInMillis = _state.value.sessionStart
        if (sessionStartInMillis != null) {
            val date = Date(sessionStartInMillis)
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return format.format(date)
        } else {
            return "TimeStamp no disponible"
        }
    }
}
