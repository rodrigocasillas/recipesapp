package com.example.viapath.recipedetail.domain.dto

import androidx.room.Entity
import com.example.viapath.recipedetail.data.network.entities.response.Measures
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.ExtendedIngredient

@Entity
data class ExtendedIngredientDto(
    val aisle: String,
    val amount: Double,
    val consistency: String,
    val id: Int,
    val image: String,
    val measures: Measures,
    val meta: List<String>,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val unit: String
)
