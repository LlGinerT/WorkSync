package com.synctech.worksync.ui.session

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.synctech.worksync.domain.exceptions.SessionError
import com.synctech.worksync.domain.models.EmployeeDomainModel
import com.synctech.worksync.domain.models.WorkSessionDomainModel
import com.synctech.worksync.domain.useCases.RestoreWorkSessionUseCase
import com.synctech.worksync.domain.useCases.StartWorkSessionUseCase
import com.synctech.worksync.domain.useCases.UpdateWorkSessionUseCase
import com.synctech.worksync.ui.models.EmployeeUiModel
import com.synctech.worksync.ui.models.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.UUID

/**
 * ViewModel compartido que gestiona la sesión activa del usuario autenticado.
 *
 * Este ViewModel:
 * - Almacena el usuario logueado.
 * - Inicia y gestiona un cronómetro que cuenta los segundos trabajados.
 * - Guarda una sesión de trabajo al cerrar sesión.
 *
 * @property updateWorkSessionUseCase Caso de uso para guardar una sesión de trabajo al finalizar.
 */
class SessionViewModel(
    private val startWorkSessionUseCase: StartWorkSessionUseCase,
    private val restoreWorkSessionUseCase: RestoreWorkSessionUseCase,
    private val updateWorkSessionUseCase: UpdateWorkSessionUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SessionState())
    val state: StateFlow<SessionState> = _state.asStateFlow()

    val currentUser: EmployeeDomainModel? get() = _state.value.employee
    val uiEmployee: EmployeeUiModel? get() = _state.value.employee?.toUi()
    val isLoggedIn: Boolean get() = _state.value.employee != null
    val isAdmin: Boolean get() = _state.value.employee?.isAdmin == true


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
            }
        }
    }

    /**
     * Detiene el cronómetro activo, si lo hay.
     */
    private fun stopTimer() {
        timerJob?.cancel()
    }

    private fun restoreSession(user: EmployeeDomainModel, session: WorkSessionDomainModel) {
        val elapsedSeconds = ((System.currentTimeMillis() - session.startTime) / 1000).toInt()

        _state.value = SessionState(
            employee = user, sessionStart = session.startTime, secondsWorked = elapsedSeconds
        )

        startTimer()
    }


    /**
     * Inicia una nueva sesión de trabajo o restaura una sesión anterior si existe.
     *
     * Este método se llama al hacer login. Verifica si ya hay una sesión activa
     * en curso (sin `endTime`) para restaurarla. Si no la hay, inicia una nueva sesión
     * y la guarda a través del caso de uso [StartWorkSessionUseCase].
     *
     * @param user Usuario autenticado.
     * @return [Result.success] si se restauró o inició sesión correctamente, o [Result.failure] si falló.
     */
    suspend fun login(user: EmployeeDomainModel): Result<Unit> {
        val restoreResult = restoreWorkSessionUseCase(user.userId)

        return restoreResult.fold(onSuccess = { session ->
            if (session != null) {
                restoreSession(user, session)
                Log.i(
                    "SessionViewModel", "Sesión restaurada automáticamente para ${user.userId}"
                )
                Result.success(Unit)
            } else {
                val start = System.currentTimeMillis()

                val result = startWorkSessionUseCase(user.userId, start)

                result.onSuccess {
                    _state.value = SessionState(
                        employee = user, sessionStart = start, secondsWorked = 0
                    )
                    Log.i(
                        "SessionViewModel", "Sesión iniciada correctamente para ${user.userId}"
                    )
                    startTimer()
                }.onFailure { error ->
                    Log.e("SessionViewModel", "Error al iniciar sesión", error)
                }

                result
            }
        }, onFailure = { error ->
            Log.e("SessionViewModel", "Error al restaurar sesión", error)
            Result.failure(error)
        })
    }


    /**
     * Finaliza la sesión de trabajo activa.
     *
     * Este método:
     * - Detiene el cronómetro
     * - Guarda la sesión en el repositorio (Firebase en producción)
     * - Limpia el estado de sesión si la operación fue exitosa
     *
     * @return [Result.success] si se guardó correctamente, o [Result.failure] con [SessionError] si falló.
     */
    suspend fun logout(): Result<Unit> {
        val user = _state.value.employee
        val start = _state.value.sessionStart
        val end = System.currentTimeMillis()

        if (user == null || start == null) {
            Log.w("SessionViewModel", "Logout cancelado: no hay sesión activa")
            return Result.failure(SessionError.IncompleteSession)
        }

        stopTimer()

        // TODO: Cuando usemos firebase aquí se usara el ID original de la sesión
        val sessionId = UUID.randomUUID().toString()

        val result = updateWorkSessionUseCase(
            sessionId = sessionId, userId = user.userId, sessionStart = start, sessionEnd = end
        )

        return result.fold(onSuccess = {
            Log.i("SessionViewModel", "Sesión guardada correctamente")
            clearSessionState()
            Result.success(Unit)
        }, onFailure = { error ->
            Log.e("SessionViewModel", "Error al guardar sesión", error)
            Result.failure(error)
        })
    }


    private fun clearSessionState() {
        stopTimer()
        _state.value = SessionState() //Función recursiva que reinicia el estado completo
    }
}
