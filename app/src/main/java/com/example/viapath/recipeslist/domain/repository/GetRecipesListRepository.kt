package com.example.viapath.recipeslist.domain.repository

import com.example.viapath.recipeslist.data.network.entities.request.RecipesListRequest
import com.example.viapath.recipeslist.domain.dto.RecipeDto
import com.example.viapath.recipeslist.domain.dto.RecipeResponseDto
import com.example.viapath.recipeslist.domain.util.OperationResult

interface GetRecipesListRepository {
    suspend fun getRecipesList(
        recipesListRequest: RecipesListRequest
    ): OperationResult<RecipeResponseDto>
}