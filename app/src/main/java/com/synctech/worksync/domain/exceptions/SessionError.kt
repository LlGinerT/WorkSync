package com.synctech.worksync.domain.exceptions

sealed class SessionError(message: String) : Exception(message) {

    object SaveFailed : SessionError("Error al guardar la sesión")
    object RestoreSessionFailed : SessionError("No se pudo restaurar la sesión")
    object NotLoggedIn : SessionError("No hay sesión iniciada")
    object IncompleteSession : SessionError("Faltan datos de la sesión")
}