package com.example.viapath.recipeslist.data.network.entities.response

import com.example.viapath.recipeslist.domain.dto.RecipeResponseDto

data class RecipeResponseEntity(
    val baseUri: String,
    val expires: Long,
    val isStale: Boolean,
    val number: Int,
    val offset: Int,
    val processingTimeMs: Int,
    val results: List<ResultEntity>,
    val totalResults: Int
)

fun RecipeResponseEntity.toRecipeResponseDto(): RecipeResponseDto {
    return RecipeResponseDto(
        baseUri = baseUri,
        expires = expires,
        isStale = isStale,
        number = number,
        offset = offset,
        processingTimeMs = processingTimeMs,
        results = results.map { it.toRecipeDto() },
        totalResults = totalResults
    )
}