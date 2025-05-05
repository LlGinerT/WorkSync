package com.synctech.worksync.ui.uiUtils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formatea un valor de segundos en formato HH:mm:ss.
 */
fun secondsToTimeString(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60
    return "%02d:%02d:%02d".format(hours, minutes, secs)
}

/**
 * Convierte un timestamp en milisegundos a una cadena formateada.
 */
fun timestampToString(millis: Long?): String {
    return millis?.let {
        val date = Date(it)
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        format.format(date)
    } ?: "TimeStamp no disponible"
}