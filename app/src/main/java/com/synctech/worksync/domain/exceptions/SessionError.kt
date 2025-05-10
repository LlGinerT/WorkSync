package com.synctech.worksync.domain.exceptions

sealed class SessionError(message: String) : Exception(message) {

    data object SaveFailed : SessionError("Error al guardar la sesión")
    data object RestoreSessionFailed : SessionError("No se pudo restaurar la sesión")
    data object NotLoggedIn : SessionError("No hay sesión iniciada")
    data object IncompleteSession : SessionError("Faltan datos de la sesión")
}