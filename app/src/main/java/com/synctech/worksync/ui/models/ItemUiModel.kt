package com.synctech.worksync.ui.models

import com.synctech.worksync.domain.models.ItemsDomainModel



/**
 * Modelo de datos para representar un material individual en la UI.
 *
 * @param itemId Identificador único del material.
 * @param name Nombre del material.
 * @param precio Precio del material.
 * @param cantidad Cantidad disponible del material (puede ser 0 si no hay unidades).
 */
data class ItemUiModel(
    val itemId: Int,
    val name: String,
    val precio: Double,
    val cantidad: Int
)

/**
 * Convierte un objeto de tipo [ItemsDomainModel] del dominio a su representación en la UI.
 *
 * @receiver [ItemsDomainModel] Modelo de dominio de un material individual.
 * @return [ItemUiModel] Modelo de UI con los datos transformados.
 */
fun ItemsDomainModel.toUi() = ItemUiModel(
    itemId = materialId,
    name = name,
    precio = precio,
    cantidad = cantidad
)
