package com.example.viapath.recipeslist.presentation.recipeslistactivity.models


data class RecipeResponse(
    val baseUri: String,
    val expires: Long,
    val isStale: Boolean,
    val number: Int,
    val offset: Int,
    val processingTimeMs: Int,
    val recipes: List<Recipe>,
    val totalResults: Int
)
