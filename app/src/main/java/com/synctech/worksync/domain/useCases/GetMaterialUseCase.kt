package com.synctech.worksync.domain.useCases

import com.synctech.worksync.data.testData.MockMaterialDataRepository
import com.synctech.worksync.domain.models.Material

/**
 * Caso de uso para obtener la lista de materiales desde el repositorio.
 *
 * @param materialRepository Repositorio de materiales utilizado para obtener los datos.
 */
class GetMaterialUseCase(
    private val materialRepository: MockMaterialDataRepository
) {

    /**
     * Ejecuta el caso de uso para obtener los materiales.
     *
     * @return Lista de materiales disponibles.
     */
    operator fun invoke(): List<Material> {
        return materialRepository.getMaterials()
    }
}
