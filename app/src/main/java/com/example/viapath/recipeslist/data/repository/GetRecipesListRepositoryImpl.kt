package com.example.viapath.recipeslist.data.repository

import com.example.viapath.recipeslist.commons.APIConstants
import com.example.viapath.recipeslist.commons.Constants
import com.example.viapath.recipeslist.data.network.RecipesListService
import com.example.viapath.recipeslist.data.network.entities.request.RecipesListRequest
import com.example.viapath.recipeslist.data.network.entities.response.toRecipeDto
import com.example.viapath.recipeslist.data.network.entities.response.toRecipeResponseDto
import com.example.viapath.recipeslist.domain.dto.RecipeDto
import com.example.viapath.recipeslist.domain.dto.RecipeResponseDto
import com.example.viapath.recipeslist.domain.repository.GetRecipesListRepository
import com.example.viapath.recipeslist.domain.util.OperationResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRecipesListRepositoryImpl @Inject constructor(
    private val recipesListService: RecipesListService
): GetRecipesListRepository {
    override suspend fun getRecipesList(
        recipesListRequest: RecipesListRequest
    ): OperationResult<RecipeResponseDto> {
        return try {
            val recipesListResult = recipesListService.getRecipesList(
                recipesListRequest.query,
                recipesListRequest.apiKey
            ).toRecipeResponseDto()
            OperationResult.Success(recipesListResult)
        } catch (e: HttpException) {
            OperationResult.Error(APIConstants.HTTP_EXCEPTION)
        } catch (e: IOException) {
            OperationResult.Error(APIConstants.IO_EXCEPTION)
        }
    }
}
