package com.synctech.worksync.domain.useCases

import android.util.Log
import com.synctech.worksync.domain.domainModels.WorkSessionDomainModel
import com.synctech.worksync.domain.repositories.WorkSessionRepository

/**
 * Caso de uso para guardar una sesión de trabajo completa en el repositorio.
 *
 * Este caso de uso se encarga de:
 * - Calcular la duración de la sesión en segundos a partir de dos timestamps (inicio y fin).
 * - Generar un identificador único para la sesión.(posiblemente inecesario al implementar Firebase)
 * - Crear una instancia de `WorkSessionDomainModel`.
 * - Enviarla al repositorio para su almacenamiento.
 *
 *
 * @property repository Repositorio responsable de guardar la sesión.
 */

class SaveWorkSessionUseCase(private val repository: WorkSessionRepository) {

    /**
     * Ejecuta el guardado de una sesión de trabajo.
     *
     * @param userID ID del trabajador al que pertenece la sesión.
     * @param sessionStart Marca de tiempo (en milisegundos) del inicio de la jornada.
     * @param sessionEnd Marca de tiempo (en milisegundos) del final de la jornada.
     */
    suspend operator fun invoke(userID: String, sessionStart: Long, sessionEnd: Long) {
        val sessionDurationInSeconds = (sessionEnd - sessionStart) / 1000
        val session = WorkSessionDomainModel(
            sessionId = java.util.UUID.randomUUID().toString(),
            userId = userID,
            startTime = sessionStart,
            endTime = sessionEnd,
            durationInSeconds = sessionDurationInSeconds
        )
        repository.saveWorkSession(session)

        //Bloque Try/Catch con fines de Debug
        try {
            repository.getWorkSessionsByUser(userID)
            Log.i("SaveWorkSessionUseCase","Sesion guardada: ${repository.getWorkSessionsByUser(userID)}")
        }catch (e: Exception){
            Log.w("SaveWorkSessionUseCase","Sesion no encontrada en el repositorio",e)
        }
    }
}