package com.example.viapath.recipedetail.domain.usecase

import com.example.viapath.recipedetail.data.network.entities.request.RecipeDetailRequest
import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto
import com.example.viapath.recipedetail.domain.dto.toRecipeDetailDto
import com.example.viapath.recipedetail.domain.repository.RecipeDetailsRepository
import com.example.viapath.recipedetail.domain.util.OperationResult
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.RecipeDetail

import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val getRecipeDetailsRepository: RecipeDetailsRepository
): BaseUseCase<RecipeDetailRequest, OperationResult<RecipeDetail>> {
    override suspend operator fun invoke(
        recipeDetailRequest: RecipeDetailRequest
    ): OperationResult<RecipeDetail> {
        val recipeDetail = getRecipeDetailsRepository.getRecipeById(recipeDetailRequest.recipeId.toInt())
        return when(recipeDetail != null && (recipeDetail.timestamp > System.currentTimeMillis())) {
            true -> {
                OperationResult.Success(recipeDetail.toRecipeDetailDto())
            }
            else -> {
                val result = getRecipeDetailsRepository.getRecipeDetails(recipeDetailRequest)
                getRemoteRecipeDetail(result)
            }
        }
    }

    private fun getRemoteRecipeDetail(
        operationResult: OperationResult<RecipeDetailDto>
    ): OperationResult<RecipeDetail> {
        return when(operationResult) {
            is OperationResult.Success -> {
                OperationResult.Success(operationResult.data?.toRecipeDetailDto())
            }
            else -> {
                OperationResult.Error(operationResult.message.toString())
            }
        }
    }
}
