package com.example.viapath.recipedetail.data.network.entities.response

import com.example.viapath.recipedetail.commons.Constants
import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.RecipeDetail

data class RecipeDetailResponseEntity(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String,
    val cuisines: List<Any>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredientEntity>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Int,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String?,
    val lowFodmap: Boolean,
    val occasions: List<Any>,
    val originalId: Any?,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val winePairing: WinePairing
)

fun RecipeDetailResponseEntity.toRecipeDetailDto(): RecipeDetailDto {
    return RecipeDetailDto(
        extendedIngredients = extendedIngredients.joinToString("\n") { it.original },
        instructions = instructions?: Constants.EMPTY,
        summary = summary,
        title = title,
        timestamp = System.currentTimeMillis() + Constants.SEVEN_DAYS,
        id = id,
    )
}
