package com.synctech.worksync.domain.models

data class Materials(
    val title: String,
    val materials: List<Material>
)


data class Material(
    val materialId: Int,
    val name: String,
    val precio: Double,
    val cantidad: Int //puede ser que haya unidades o no
)