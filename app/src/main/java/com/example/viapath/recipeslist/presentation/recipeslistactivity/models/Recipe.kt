package com.example.viapath.recipeslist.presentation.recipeslistactivity.models

data class Recipe(
    val id: Int,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)
