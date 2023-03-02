package com.example.viapath.recipedetail.domain.usecase

import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto
import com.example.viapath.recipedetail.domain.repository.RecipeDetailsRepository
import javax.inject.Inject

class InsertRecipeDetailUseCase @Inject constructor(
    private val getRecipeDetailsRepository: RecipeDetailsRepository
): BaseUseCase<RecipeDetailDto, Unit> {
    override suspend operator fun invoke(
        recipeDetailDto: RecipeDetailDto
    ) {
        getRecipeDetailsRepository.insertRecipe(recipeDetailDto)
    }
}