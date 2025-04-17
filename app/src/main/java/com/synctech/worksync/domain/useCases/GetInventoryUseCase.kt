package com.synctech.worksync.domain.useCases

import com.synctech.worksync.data.testData.MockInventoryDataRepository
import com.synctech.worksync.domain.models.ItemsDomainModel

/**
 * Caso de uso para obtener la lista de materiales desde el repositorio.
 *
 * @param materialRepository Repositorio de materiales utilizado para obtener los datos.
 */
class GetInventoryUseCase(
    private val materialRepository: MockInventoryDataRepository
) {

    /**
     * Ejecuta el caso de uso para obtener los materiales.
     *
     * @return Lista de materiales disponibles.
     */
    operator fun invoke(): List<ItemsDomainModel> {
        return materialRepository.getMaterials()
    }
}
