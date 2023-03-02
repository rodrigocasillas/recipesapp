package com.example.viapath.recipedetail.domain.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.RecipeDetail

@Entity
data class RecipeDetailDto(
    val extendedIngredients: String,
    val instructions: String,
    val summary: String,
    val title: String,
    val timestamp: Long,
    @PrimaryKey val id: Int? = null
)

fun RecipeDetailDto.toRecipeDetailDto(): RecipeDetail {
    return RecipeDetail(
        extendedIngredients = extendedIngredients,
        instructions = instructions,
        summary = summary,
        title = title,
        timestamp = timestamp,
        id = id?:0
    )
}