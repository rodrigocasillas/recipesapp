package com.example.viapath.recipedetail.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipedetaildto WHERE id = :id")
    suspend fun getRecipeById(id: Int): RecipeDetailDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeDetailDto: RecipeDetailDto)
}