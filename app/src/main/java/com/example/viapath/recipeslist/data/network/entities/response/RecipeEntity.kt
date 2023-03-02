package com.example.viapath.recipeslist.data.network.entities.response

import com.example.viapath.recipeslist.domain.dto.RecipeDto

data class ResultEntity(
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)

fun ResultEntity.toRecipeDto(): RecipeDto {
    return RecipeDto(
        id = id,
        image = image,
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceUrl = sourceUrl,
        title = title
    )
}