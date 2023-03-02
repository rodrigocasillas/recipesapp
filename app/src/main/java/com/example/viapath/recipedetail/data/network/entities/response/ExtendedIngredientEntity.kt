package com.example.viapath.recipedetail.data.network.entities.response

import com.example.viapath.recipedetail.commons.Constants
import com.example.viapath.recipedetail.domain.dto.ExtendedIngredientDto

data class ExtendedIngredientEntity(
    val aisle: String?,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String?,
    val measures: Measures,
    val meta: List<String>,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val unit: String
)

fun ExtendedIngredientEntity.toExtendedIngredientDto(): ExtendedIngredientDto {
    return ExtendedIngredientDto(
        aisle = aisle?: Constants.EMPTY,
        amount = amount,
        consistency = consistency,
        id = id,
        image = image?: Constants.EMPTY,
        measures = measures,
        meta = meta,
        name = name,
        nameClean = nameClean,
        original = original,
        originalName = originalName,
        unit = unit
    )
}