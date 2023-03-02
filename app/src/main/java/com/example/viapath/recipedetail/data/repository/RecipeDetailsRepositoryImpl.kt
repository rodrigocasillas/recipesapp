package com.example.viapath.recipedetail.data.repository

import com.example.viapath.recipedetail.commons.APIConstants
import com.example.viapath.recipedetail.data.datasource.RecipeDao
import com.example.viapath.recipedetail.data.network.RecipeDetailsService
import com.example.viapath.recipedetail.data.network.entities.request.RecipeDetailRequest
import com.example.viapath.recipedetail.data.network.entities.response.toRecipeDetailDto
import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto
import com.example.viapath.recipedetail.domain.repository.RecipeDetailsRepository
import com.example.viapath.recipedetail.domain.util.OperationResult
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class RecipeDetailsRepositoryImpl @Inject constructor(
    private val recipeDetailService: RecipeDetailsService,
    private val dao: RecipeDao
): RecipeDetailsRepository {

    override suspend fun getRecipeDetails(
        recipeDetailRequest: RecipeDetailRequest
    ): OperationResult<RecipeDetailDto> {
        return try {
            val recipeDetails = recipeDetailService.getRecipeDetail(
                recipeId = recipeDetailRequest.recipeId,
                apiKey = recipeDetailRequest.apiKey

            ).toRecipeDetailDto()
            OperationResult.Success(recipeDetails)
        } catch (e: HttpException) {
            OperationResult.Error(APIConstants.HTTP_EXCEPTION)
        } catch (e: IOException) {
            OperationResult.Error(APIConstants.IO_EXCEPTION)
        }
    }

    override suspend fun getRecipeById(id: Int): RecipeDetailDto {
        return dao.getRecipeById(id)
    }

    override suspend fun insertRecipe(recipeDetailDto: RecipeDetailDto) {
        dao.insertRecipe(recipeDetailDto)
    }
}