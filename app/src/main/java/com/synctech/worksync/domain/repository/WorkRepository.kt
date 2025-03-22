package com.synctech.worksync.domain.repository

import com.synctech.worksync.domain.models.Work

/**
 * Interfaz que define el contrato para un repositorio de trabajos.
 * Esta interfaz debe ser implementada por cualquier clase que proporcione datos de trabajos.
 */
interface WorkRepository {

    /**
     * Obtiene una lista de trabajos.
     *
     * @return Una lista de objetos [Work] que representan los trabajos disponibles.
     */
    fun getWork(): List<Work>
}
