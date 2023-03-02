package com.example.viapath.recipedetail.presentation.recipesdetailactivity.models

import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto

data class RecipeDetail(
    val extendedIngredients: String,
    val instructions: String,
    val summary: String,
    val title: String,
    val timestamp: Long,
    val id: Int
)

fun RecipeDetail.getInstructions(): String {
    return instructions.ifBlank { "There are no instructions for these recipe" }
}

fun RecipeDetail.toRecipeDetailDto(): RecipeDetailDto {
    return RecipeDetailDto(
        extendedIngredients = extendedIngredients,
        instructions = instructions,
        summary = summary,
        title = title,
        timestamp = timestamp,
        id = id
    )
}