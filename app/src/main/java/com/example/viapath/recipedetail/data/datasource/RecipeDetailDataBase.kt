package com.example.viapath.recipedetail.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.viapath.recipedetail.domain.dto.RecipeDetailDto

@Database(
    entities = [RecipeDetailDto::class],
    version = 1
)

abstract class RecipeDetailDataBase: RoomDatabase() {
    abstract val recipeDao: RecipeDao
    companion object {
        const val DATABASE_NAME = "recipe_detail_db"
    }
}