package com.example.viapath.recipedetail.data.network.entities.request

import com.example.viapath.BuildConfig

data class RecipeDetailRequest(
    val recipeId: String,
    val apiKey: String = BuildConfig.APY_KEY
)
