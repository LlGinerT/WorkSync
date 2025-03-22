package com.synctech.worksync.domain.useCases

import com.synctech.worksync.domain.repository.WorkRepository
import com.synctech.worksync.domain.models.Work

/**
 * Clase que representa un caso de uso para obtener una lista de trabajos desde el repositorio.
 *
 * @param workRepository El repositorio de trabajos, donde se obtiene la información.
 */
class GetWorkUseCase(
    private val workRepository: WorkRepository
) {

    /**
     * Obtiene una lista de trabajos desde el repositorio.
     *
     * @return Una lista de objetos [Work], representando los trabajos obtenidos.
     */
    operator fun invoke(): List<Work> {
        return workRepository.getWork()
    }
}
