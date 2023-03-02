package com.example.viapath.recipedetail.domain.repository

import com.example.viapath.recipedetail.data.network.entities.request.RecipeDetailRequest
import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto
import com.example.viapath.recipedetail.domain.util.OperationResult


interface RecipeDetailsRepository {
    suspend fun getRecipeDetails(
       recipeDetailRequest: RecipeDetailRequest
    ): OperationResult<RecipeDetailDto>
    suspend fun getRecipeById(id: Int): RecipeDetailDto?
    suspend fun insertRecipe(recipeDetailDto: RecipeDetailDto)
}