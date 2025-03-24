package com.synctech.worksync.ui.session

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.domainModels.WorkerDomainModel
import com.synctech.worksync.domain.useCases.SaveWorkSessionUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.Instant

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

    private val _worker = MutableStateFlow<WorkerDomainModel?>(null)
    val worker: StateFlow<WorkerDomainModel?> = _worker.asStateFlow()

    // Contador interno en segundos desde que inició la jornada
    private val _secondsWorked = MutableStateFlow(0)
    val secondsWorked: StateFlow<Int> = _secondsWorked.asStateFlow()

    // Timestamps de inicio y fin de sesión (en milisegundos). Se cambiarán por Firebase Timestamp más adelante.
    private var sessionStart: Long? = null
    private var sessionEnd: Long? = null

    // Job que representa la corutina activa del cronómetro
    private var timerJob: Job? = null

    /**
     * Inicia el cronómetro si no está ya activo y almacena un TimeStamp.
     */
    private fun startTimer() {
        sessionStart = System.currentTimeMillis() // ⚠️ TODO: Sustituir por Firebase Timestamp

        if (timerJob?.isActive == true) return
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000)
                _secondsWorked.value += 1
                Log.i("SessionViewModel", "Crono funciona: ${getFormattedWorkedTime()}")
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
        _worker.value = workerDomainModel
        startTimer()
    }

    /**
     * Finaliza la sesión del usuario:
     * - Detiene el cronómetro.
     * - Guarda la sesión de trabajo mediante el caso de uso.
     * - Limpia el estado del ViewModel.
     */
    fun logout() {
        val end = System.currentTimeMillis()
        val start = sessionStart
        val user = _worker.value

        if (user != null && start != null) {
            viewModelScope.launch {
                try {
                    saveWorkSessionUseCase(user.userId, start, end)
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
        _worker.value = null
        sessionStart = null
        _secondsWorked.value = 0
    }

    /**
     *  Devuelve el tiempo trabajado formateado.
     *
     *  @return Tiempo trabajado en formato HH:MM:SS
     */
    fun getFormattedWorkedTime(): String {
        val totalSeconds = _secondsWorked.value
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

}
