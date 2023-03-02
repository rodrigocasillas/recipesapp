package com.example.viapath.recipeslist.data.network

import com.example.viapath.recipeslist.commons.APIConstants
import com.example.viapath.recipeslist.data.network.entities.response.RecipeResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesListService {

    @GET(APIConstants.GET_RECIPES_LIST_ENDPOINT)
    suspend fun getRecipesList(
        @Query(APIConstants.QUERY) query: String,
        @Query(APIConstants.API_KEY) apiKey: String
    ): RecipeResponseEntity
}