package com.example.viapath.recipedetail.data.network

import com.example.viapath.recipedetail.commons.APIConstants
import com.example.viapath.recipedetail.data.network.entities.response.RecipeDetailResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeDetailsService {
    @GET("recipes/{${APIConstants.RECIPE_ID}}/information/")
    suspend fun getRecipeDetail(
        @Path(APIConstants.RECIPE_ID) recipeId: String,
        @Query(APIConstants.API_KEY) apiKey: String
    ): RecipeDetailResponseEntity
}