package com.example.viapath.recipeslist.data.network.entities.request

import com.example.viapath.BuildConfig

data class RecipesListRequest(
    val query: String,
    val apiKey: String = BuildConfig.APY_KEY
)
