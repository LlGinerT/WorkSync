package com.synctech.worksync.domain.repositories

import com.synctech.worksync.domain.models.Material
import com.synctech.worksync.domain.models.User
import com.synctech.worksync.domain.models.Work

interface MaterialRepository {

    fun getMaterials() :List<Material>

    fun addMaterials(user: User, material: Material):Boolean

    fun removeMaterials(user: User, material: Material): Boolean
}