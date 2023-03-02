package com.example.viapath.recipeslist.domain.dto

import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.RecipeResponse

data class RecipeResponseDto(
    val baseUri: String,
    val expires: Long,
    val isStale: Boolean,
    val number: Int,
    val offset: Int,
    val processingTimeMs: Int,
    val results: List<RecipeDto>,
    val totalResults: Int
)

fun RecipeResponseDto.toRecipeResponse(): RecipeResponse {
    return RecipeResponse(
        baseUri = baseUri,
        expires = expires,
        isStale = isStale,
        number = number,
        offset = offset,
        processingTimeMs = processingTimeMs,
        recipes = results.map { it.toRecipe() },
        totalResults = totalResults
    )
}
