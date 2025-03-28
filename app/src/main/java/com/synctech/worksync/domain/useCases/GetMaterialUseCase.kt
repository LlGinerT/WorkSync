package com.synctech.worksync.domain.useCases

import com.synctech.worksync.data.testData.MockMaterialDataRepository
import com.synctech.worksync.domain.models.Material

class GetMaterialUseCase(
    private val materialRepository: MockMaterialDataRepository
) {


    operator fun invoke(): List<Material> {
        return materialRepository.getMaterials()
    }
}