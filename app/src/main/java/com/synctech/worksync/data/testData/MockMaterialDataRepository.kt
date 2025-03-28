package com.synctech.worksync.data.testData

import com.synctech.worksync.domain.models.Material

import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.repositories.MaterialRepository

class MockMaterialDataRepository: MaterialRepository {
    private val materials = mutableListOf(
        Material(
            materialId = 1,
            name =  "Cable De Red",
            precio =  100.50,
            cantidad = 35
        ),

        Material(
            materialId = 2,
            name =  "Adaptador RJ45",
            precio =  170.50,
            cantidad = 60
        ),

        Material(
            materialId = 3,
            name =  "Módem",
            precio =  65.50,
            cantidad = 120
        ),

        Material(
            materialId = 4 ,
            name =  "Switch de Red",
            precio =  80.50,
            cantidad = 46
        ),

    )

    override fun getMaterials(): List<Material> {
        return materials
    }

    override fun addMaterials(user: User, material: Material): Boolean {
        return true
    }

    override fun removeMaterials(user: User, material: Material): Boolean {
        return true
    }
}