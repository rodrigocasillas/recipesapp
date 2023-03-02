package com.example.viapath.recipeslist.domain.usecase

import com.example.viapath.recipeslist.data.network.entities.request.RecipesListRequest
import com.example.viapath.recipeslist.domain.dto.toRecipeResponse
import com.example.viapath.recipeslist.domain.repository.GetRecipesListRepository
import com.example.viapath.recipeslist.domain.util.OperationResult
import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.RecipeResponse
import javax.inject.Inject

class GetRecipesListUseCase @Inject constructor(
    private val getRecipesListRepository: GetRecipesListRepository
): BaseUseCase<RecipesListRequest, OperationResult<RecipeResponse>> {
    override suspend operator fun invoke(recipesListRequest: RecipesListRequest): OperationResult<RecipeResponse> {
        return when(val result = getRecipesListRepository.getRecipesList(recipesListRequest)) {
            is OperationResult.Success -> {
                OperationResult.Success(result.data?.toRecipeResponse())
            }
            else -> {
                OperationResult.Error(result.message.toString())
            }
        }
    }
}