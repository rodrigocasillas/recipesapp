package com.example.viapath.recipeslist.domain.dto

import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.Recipe

data class RecipeDto(
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = id,
        image = image,
        readyInMinutes = readyInMinutes,
        servings = servings,
        sourceUrl = sourceUrl,
        title = title
    )
}